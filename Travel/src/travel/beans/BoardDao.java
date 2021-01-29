package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import travel.util.JdbcUtil;

public class BoardDao {
	public static final String USERNAME = "semi";
	public static final String PASSWORD = "semi";
	private String cate;


	////////////////////////////////////////////////////////////////////////////////////////////////////

	//변수 cate에 값 저장 메소드
	public void setCate(String cate) {
		this.cate = cate;
	}

	//말머리 확인 메소드
	public boolean checkIsNotHead(String board_head) {
		if(cate.equals("자유")) {
			if(Objects.isNull(board_head) || (!board_head.equals("질문") && !board_head.equals("사담"))) {
				return true;
			}
		}
		else if(cate.equals("여행")) {
			if(Objects.isNull(board_head) || 
					(!board_head.equals("강원도") && !board_head.equals("서울") &&
							!board_head.equals("인천") && !board_head.equals("경기도") &&
							!board_head.equals("충남") && !board_head.equals("충북") &&
							!board_head.equals("경북") && !board_head.equals("경남") &&
							!board_head.equals("대구") && !board_head.equals("대전") &&
							!board_head.equals("세종") && !board_head.equals("울산") &&
							!board_head.equals("부산") && !board_head.equals("전북") &&
							!board_head.equals("전남") && !board_head.equals("광주") &&
							!board_head.equals("제주도"))) {
				return true;
			}
		}else if(cate.equals("전체")) {
			if(Objects.isNull(board_head) || (!board_head.equals("자유") && !board_head.equals("여행"))) {
				return true;
			}
		}

		return false;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	//전체 게시판 게시글 목록 조회
	//페이지 번호에 따른 게시글 목록 조회
	public List<BoardDto> selectEntire(int startRow, int endRow) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startRow);
		ps.setInt(2, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//페이지에 따른 검색 목록 조회
	public List<BoardDto> selectEntire(int startRow, int endRow, String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ "	where instr(#1, ?) >0"
				+ "	order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//전체 게시판 말머리 + 검색 게시글 조회
	public List<BoardDto> selectEntire(int startRow, int endRow, String type, String key, String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectEntire(startRow, endRow, type, key);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ "	where board_cate = ? and instr(#1, ?) >0"
				+ "	order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//전체 게시판 only 말머리 목록 조회
	public List<BoardDto> selectEntire(int startRow, int endRow, String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectEntire(startRow, endRow);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ " where board_cate = ? order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;

	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////

	//자유, 여행 게시판		

	//게시글 목록 개수
	public int selectEntireCount() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	//검색 게시글 목록 개수
	public int selectEntireCount(String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board "
				+ "where instr(#1, ?)>0";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	//말머리 + 검색 게시글 수
	public int selectEntireCount(String type, String key, String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectEntireCount(type, key);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board "
				+ "where board_cate = ? and instr(#1, ?)>0";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setString(2, key);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	//말머리 검색 게시글 수
	public int selectEntireCount(String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectEntireCount();
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board "
				+ "where board_cate = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////

	//게시글 목록 조회
	public List<BoardDto> select() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql ="select * from board where board_cate = ? order by board_no desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//게시글 검색 목록 조회
	public List<BoardDto> select(String key, String value) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql ="select * from board where board_cate = ? and instr(#1, ?) > 0 "
				+ "order by board_no desc";
		sql = sql.replace("#1", key);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, value);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//페이지 번호에 따른 게시글 목록 조회
	public List<BoardDto> selectByPage(int startRow, int endRow) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board where board_cate= ? order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//페이지에 따른 검색 목록 조회
	public List<BoardDto> selectByPage(int startRow, int endRow, String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ "	where board_cate = ? and instr(#1, ?) >0"
				+ "	order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//말머리 + 검색 목록 조회
	public List<BoardDto> selectByPage(int startRow, int endRow, String type, String key, String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectByPage(startRow, endRow, type, key);
		}


		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select * from board "
				+ "where board_cate= ? and board_head=? and instr(#1, ?) >0 "
				+ "order by board_no desc)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, board_head);
		ps.setString(3, key);
		ps.setInt(4, startRow);
		ps.setInt(5, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//only 말머리 목록 조회
	public List<BoardDto> selectByPage(int startRow, int endRow, String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectByPage(startRow, endRow);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ "	where board_cate= ? and board_head=? "
				+ "	order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, board_head);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////

	//게시글 목록 개수
	public int selectCount() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board where board_cate = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	//검색 게시글 목록 개수
	public int selectCount(String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board "
				+ "where board_cate = ? and instr(#1, ?)>0";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, key);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	//말머리 + 검색 게시글 수
	public int selectCount(String type, String key, String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectCount(type, key);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board "
				+ "where board_cate = ? and board_head = ? and instr(#1, ?)>0";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, board_head);
		ps.setString(3, key);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	//말머리 검색 게시글 수
	public int selectCount(String board_head) throws Exception{
		if(checkIsNotHead(board_head)) {
			return selectCount();
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from board "
				+ "where board_cate = ? and board_head=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, board_head);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}

	////////////////////////////////////////////////////////////////////////////////////////

	//상단고정 공지사항 조회 메소드
	public List<BoardDto> selectedNotice() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select b.* from "
				+ "board b inner join notice n on board_no = notice_board_no "
				+ "order by board_no desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}

	//상단고정 공지사항 개수 구하는 메소드
	public int selectedNoticeNum() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(notice_no) from notice";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int num = rs.getInt(1);

		con.close();

		return num;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	//공지게시판
	//페이지 번호에 따른 게시글 목록 조회
	public List<BoardDto> selectNotice(int startRow, int endRow) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select b.*, n.notice_board_no from ("
				+ "select board.* from board where board_cate= ?) b left outer join notice n on (b.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null"
				+ ")TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}
	
	//페이지 번호에 따른 검색 게시글 목록 조회
	public List<BoardDto> selectNotice(int startRow, int endRow, String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select b.*, n.notice_board_no from ("
				+ "select board.* from board where board_cate= ? and instr(#1, ?) > 0) b left outer join notice n on (b.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null"
				+ ")TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			BoardDto dto = new BoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}

		con.close();

		return list;
	}
	
	//게시글 목록 개수
	public int selectNoticeCount() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from("
				+ "select b.board_no, n.notice_board_no from ("
				+ "select * from board where board_cate= ?) b left outer join notice n on (b.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}
	
	//검색 게시글 목록 개수
	public int selectNoticeCount(String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from("
				+ "select b.board_no, n.notice_board_no from ("
				+ "select * from board where board_cate= ? and instr(#1, ?) > 0) b left outer join notice n on (b.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null)";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, key);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		con.close();
		return count;
	}
	
	
}



























