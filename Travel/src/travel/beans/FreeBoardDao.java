package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import travel.util.JdbcUtil;

public class FreeBoardDao {
	public static final String USERNAME = "semi";
	public static final String PASSWORD = "semi";
	
	//게시글 작성
		public void insert(FreeBoardDto dto) throws Exception{
			Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
			
			String sql = "insert into board values(BOARD_SEQ.nextval, 'admin', ?, ?, ?, ?, sysdate, 0, 0)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBoard_cate());
			ps.setString(2, dto.getBoard_head());
			ps.setString(3, dto.getBoard_title());
			ps.setString(4, dto.getBoard_content());
			ps.execute();
			
			con.close();
		}
		
	//게시글 목록 조회
	public List<FreeBoardDto> select() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql ="select * from board where board_cate ='자유' order by board_no desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>();
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	public List<FreeBoardDto> select(String key, String value) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql ="select * from board where board_cate ='자유' and instr(#1, ?) > 0 "
				+ "order by board_no desc";
		sql = sql.replace("#1", key);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, value);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>();
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	public List<FreeBoardDto> selectByPage(int startRow, int endRow) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board where board_cate='자유' order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startRow);
		ps.setInt(2, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	public List<FreeBoardDto> selectByPage(int startRow, int endRow, String type, String key) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ "	where board_cate = '자유' and instr(#1, ?) >0"
				+ "	order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	public List<FreeBoardDto> selectByPage(int startRow, int endRow, String type, String key, String board_head) throws Exception{
		if(board_head.equals("전체")) {
			return selectByPage(startRow, endRow, type, key);
		}
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from("
				+ "select * from board "
				+ "where board_cate='자유' and board_head=? and instr(#1, ?) >0 "
				+ "order by board_no desc)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	public List<FreeBoardDto> selectByPage(int startRow, int endRow, String board_head) throws Exception{
		if(board_head.equals("전체")) {
			return selectByPage(startRow, endRow);
		}
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from ("
				+ "	select rownum rn, TMP.* from("
				+ "	select * from board "
				+ "	where board_cate='자유' and board_head=? "
				+ "	order by board_no desc"
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	public int selectCount() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(board_no) from board where board_cate = '자유'";
		PreparedStatement ps = con.prepareStatement(sql);
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
				+ "where board_cate = '자유' and instr(#1, ?)>0";
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
	public int selectCount(String type, String key, String board_head) throws Exception{
		if(board_head.equals("전체")) {
			return selectCount(type, key);
		}
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(board_no) from board "
				+ "where board_cate = '자유' and board_head = ? and instr(#1, ?)>0";
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
	public int selectCount(String board_head) throws Exception{
		if(board_head.equals("전체")) {
			return selectCount();
		}
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(board_no) from board "
				+ "where board_cate = '자유' and board_head=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		con.close();
		return count;
	}
	
	//공지사항 조회 메소드
	public List<FreeBoardDto> selectedNotice() throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select b.* from "
				+ "board b inner join notice n on board_no = notice_board_no "
				+ "order by board_no desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>(); //조회
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
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
	
	//공지사항 개수 구하는 메소드
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
}



























