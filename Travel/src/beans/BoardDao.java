package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

	// 데이터베이스 로그인 및 비밀번호
	public static final String USERNAME = "web";
	public static final String PASSWORD = "web";

	// 목록 기능
	public List<BoardDto> select() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from board order by board_no desc";

		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<BoardDto>();

		while (rs.next()) {
			BoardDto dto = new BoardDto();

			dto.setBoard_no(rs.getInt("board_no")); // 번호
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title")); // 제목
			dto.setBoard_nick(rs.getString("board_nick")); // 작성자 닉네임
			dto.setBoard_date(rs.getDate("board_date")); // 등록일
			dto.setBoard_view(rs.getInt("board_view")); // 조회수
			dto.setBoard_like(rs.getInt("board_like")); // 추천수

			list.add(dto);

		}
		con.close();

		return list;
	}

	// 등록 기능
	public void write(BoardDto dto) throws Exception {
		//현재시간 YYYY-MM-DD HH.mm.ss
		//ex ) insert into board(~~~, board_date) values(to_char(sysdate, 'yyyy.mm.dd hh24:mi:ss));
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "insert into board("
				+ "board_no, board_nick, board_cate, board_head, board_title, board_content"
				+ ") values(board_seq.nextval, '홍길동', ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, dto.getBoard_cate()); // 대분류
		ps.setString(2, dto.getBoard_head()); // 소분류
		ps.setString(3, dto.getBoard_title()); // 제목
		ps.setString(4, dto.getBoard_content()); // 내용

		ps.execute();

		con.close();
	}

	// 검색 기능
	public List<BoardDto> select(String type, String keyword) throws Exception {

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from board where instr(#1, ?) > 0 order by board_no desc";
		
		sql = sql.replace("#1", type); //#1 = 제목,닉네임,소분류, 대분류, 내용

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>();

		while (rs.next()) {
			BoardDto dto = new BoardDto();

			dto.setBoard_no(rs.getInt("board_no")); // 번호
			dto.setBoard_head(rs.getString("board_head")); //소분류
			dto.setBoard_title(rs.getString("board_title")); // 제목
			dto.setBoard_nick(rs.getString("board_nick")); // 작성자 닉네임
			dto.setBoard_date(rs.getDate("board_date")); // 등록일
			dto.setBoard_view(rs.getInt("board_view")); // 조회수
			dto.setBoard_like(rs.getInt("board_like")); // 추천수

			list.add(dto);
		}

		con.close();

		return list;

	}
	// 상세보기
	public BoardDto find(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from board where board_no=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, board_no);
		ResultSet rs = ps.executeQuery();
		BoardDto dto;
		
		//상세보기에 필요한 것 : 번호,작성자, 제목, 내용, 조회, 추천, 날짜 및 시간, 소분류, 답글 갯수(추후 추가)
		if(rs.next()) {
			dto = new BoardDto();
			
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_content(rs.getString("board_content"));
			dto.setBoard_view(rs.getInt("board_view"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_date(rs.getDate("board_date"));
			
		}
		else {
			dto = null;
		}
		
		con.close();
		
		return dto;
	}
}
