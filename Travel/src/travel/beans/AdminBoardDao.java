package travel.beans;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import travel.admin.util.JdbcUtil;

public class AdminBoardDao {
	public static final String USERNAME = "semi_project";
	public static final String PASSWORD = "semi_project";
	
	
	
	public BoardDto boardSearch(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from board where board_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, board_no);
		ResultSet rs = ps.executeQuery();
		
		BoardDto boardDto;
		if(rs.next()) {
			boardDto = new BoardDto();
			boardDto.setBoard_no(rs.getInt("board_no"));
			boardDto.setBoard_nick(rs.getString("board_nick"));
			boardDto.setBoard_cate(rs.getString("board_cate"));
			boardDto.setBoard_head(rs.getString("board_head"));
			boardDto.setBoard_title(rs.getString("board_title"));
			boardDto.setBoard_content(rs.getClob("board_content"));
			boardDto.setBoard_view(rs.getInt("board_view"));
			boardDto.setBoard_like(rs.getInt("board_like"));
			boardDto.setBoard_date(rs.getDate("board_date"));
			boardDto.setBoard_open(rs.getInt("board_open"));
			
		}
		else {
			boardDto = null;
		}
		
		con.close();
		
		return boardDto;
	}
	public boolean boardEditByAdmin(BoardDto boardDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update board set board_cate=?, board_head=?, board_title=? board_content=? board_open=? where board_no";
		PreparedStatement ps = con.prepareStatement(sql);
		
		Clob clob = con.createClob();
		
		
		ps.setString(1, boardDto.getBoard_cate());
		ps.setString(2, boardDto.getBoard_head());
		ps.setString(3, boardDto.getBoard_title());
		ps.setClob(4, boardDto.getBoard_content());
		ps.setInt(5, boardDto.getBoard_open());
		ps.setInt(6, boardDto.getBoard_no());
		
		int count = ps.executeUpdate();
		
		con.close();
		return count > 0;
	}
	public boolean boardDelete(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete board where board_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, board_no);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count > 0;
		
	}
	

	//검색 : keyword를 이용한 아이디 "시작검사"
	public List<BoardDto> boardSelect(String keyword) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
//		String sql = "select * from member where member_id like ?||'%' order by member_id asc";
		String sql = "select * from board where instr(member_id, ?) = 1 order by board_nick asc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_no(rs.getInt("board_no"));
			boardDto.setBoard_nick(rs.getString("board_nick"));
			boardDto.setBoard_cate(rs.getString("board_cate"));
			boardDto.setBoard_head(rs.getString("board_head"));
			boardDto.setBoard_title(rs.getString("board_title"));
			boardDto.setBoard_content(rs.getClob("board_content"));
			boardDto.setBoard_view(rs.getInt("board_view"));
			boardDto.setBoard_like(rs.getInt("board_like"));
			boardDto.setBoard_date(rs.getDate("board_date"));
			boardDto.setBoard_open(rs.getInt("board_open"));
			
			list.add(boardDto);
		}
		
		con.close();
		
		return list;
	}
	
	public List<BoardDto> boardSelect(String type, String keyword) throws Exception {
//		분류나 검색어 중 하나라도 없으면 null 반환
		if(type == null || keyword == null) return null;
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from board where instr(#1, ?) > 0 order by board_nick asc";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> list = new ArrayList<>();
		while(rs.next()) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_no(rs.getInt("board_no"));
			boardDto.setBoard_nick(rs.getString("board_nick"));
			boardDto.setBoard_cate(rs.getString("board_cate"));
			boardDto.setBoard_head(rs.getString("board_head"));
			boardDto.setBoard_title(rs.getString("board_title"));
			boardDto.setBoard_content(rs.getClob("board_content"));
			boardDto.setBoard_view(rs.getInt("board_view"));
			boardDto.setBoard_like(rs.getInt("board_like"));
			boardDto.setBoard_date(rs.getDate("board_date"));
			boardDto.setBoard_open(rs.getInt("board_open"));
			
			list.add(boardDto);
		}
		
		con.close();
		
		return list;
	}
	public int getBoardSearchCount(String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(*) from board where instr(#1, ?) > 0";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
//		int count = rs.getInt("count(*)");
		con.close();
		
		return count;
	}
	
	
	public int getBoardCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(*) from board";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
//		int count = rs.getInt("count(*)");
		con.close();
		
		return count;
	}
	public List<BoardDto> boardSearchList(String type, String key, int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = 	"select * from(" + 
							"select rownum rn, TMP.* from(" + 
								"select * from board "
								+ "where instr(#1, ?) > 0 "
								+ "order by board_no desc" + 
							")TMP" + 
						") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> boardList = new ArrayList<>();
		while(rs.next()) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_no(rs.getInt("board_no"));
			boardDto.setBoard_nick(rs.getString("board_nick"));
			boardDto.setBoard_cate(rs.getString("board_cate"));
			boardDto.setBoard_head(rs.getString("board_head"));
			boardDto.setBoard_title(rs.getString("board_title"));
			boardDto.setBoard_content(rs.getClob("board_content"));
			boardDto.setBoard_view(rs.getInt("board_view"));
			boardDto.setBoard_like(rs.getInt("board_like"));
			boardDto.setBoard_date(rs.getDate("board_date"));
			boardDto.setBoard_open(rs.getInt("board_open"));
			
			boardList.add(boardDto);
		}
		
		con.close();
		
		return boardList;
	}
	
	
	public List<BoardDto> boardPagingList(int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = 	"select * from(" + 
							"select rownum rn, TMP.* from(" + 
								"select * from board order by board_no desc" + 
							")TMP" + 
						") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startRow);
		ps.setInt(2, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<BoardDto> boardList = new ArrayList<>();
		while(rs.next()) {
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_no(rs.getInt("board_no"));
			boardDto.setBoard_nick(rs.getString("board_nick"));
			boardDto.setBoard_cate(rs.getString("board_cate"));
			boardDto.setBoard_head(rs.getString("board_head"));
			boardDto.setBoard_title(rs.getString("board_title"));
			boardDto.setBoard_content(rs.getClob("board_content"));
			boardDto.setBoard_view(rs.getInt("board_view"));
			boardDto.setBoard_like(rs.getInt("board_like"));
			boardDto.setBoard_date(rs.getDate("board_date"));
			boardDto.setBoard_open(rs.getInt("board_open"));
			
			boardList.add(boardDto);
		}
		con.close();
		
		return boardList;
	}
	
	public void plusReadcount(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update board set board_view=board_view+1 where board_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, board_no);
		
		ps.execute();
//		int count = ps.executeUpdate();
		
		con.close();
	}
	
	public BoardDto find(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from board where board_no = ?";//결과가 절대로 여러개가 나올 수 없다
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, board_no);
		ResultSet rs = ps.executeQuery();
		
		//rs는 예상 결과가 1개 아니면 0개. 즉 있냐 없냐만 알면 된다.
		//목록처럼 List를 만들어서 add를 할 필요가 없다(while문이 필요 없다)
		BoardDto boardDto;
		if(rs.next()) {//결과가 있다면 객체를 만들어 데이터베이스 값을 전부다 복사하겠다
			boardDto = new BoardDto();
			boardDto.setBoard_no(rs.getInt("board_no"));
			boardDto.setBoard_nick(rs.getString("board_nick"));
			boardDto.setBoard_cate(rs.getString("board_cate"));
			boardDto.setBoard_head(rs.getString("board_head"));
			boardDto.setBoard_title(rs.getString("board_title"));
			boardDto.setBoard_content(rs.getClob("board_content"));
			boardDto.setBoard_view(rs.getInt("board_view"));
			boardDto.setBoard_like(rs.getInt("board_like"));
			boardDto.setBoard_date(rs.getDate("board_date"));
			boardDto.setBoard_open(rs.getInt("board_open"));
		}
		else {//결과가 없다면 잘못된 번호니까 null이라는 값을 반환하겠다
			boardDto = null;
		}
		
		con.close();
		
		return boardDto;
	}
	
	public boolean update(BoardDto dto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update board "
						+ "set board_cate=?, board_head=?, board_title=?, board_open=? "
						+ "where board_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getBoard_cate());
		ps.setString(2, dto.getBoard_head());
		ps.setString(3, dto.getBoard_title());
		
		ps.setInt(4, dto.getBoard_open());
		ps.setInt(5, dto.getBoard_no());
		int count = ps.executeUpdate();
		
		con.close();
		return count > 0;
//		if(count > 0) {
//			return true;
//		}
//		else {
//			return false;
//		}
	}

}
