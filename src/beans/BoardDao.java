package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import util.JdbcUtil;

public class BoardDao {

	// 데이터베이스 로그인 및 비밀번호
	public static final String USERNAME = "web";
	public static final String PASSWORD = "web";
	private String cate;

	////////////////////////////////////////////////////////////////////////////////////////////////////

	// 변수 cate에 값 저장 메소드
	public void setCate(String cate) {
		this.cate = cate;
	}

	// 말머리 확인 메소드
	public boolean checkIsNotHead(String board_head) {
		if (cate.equals("자유")) {
			if (Objects.isNull(board_head) || (!board_head.equals("질문") && !board_head.equals("사담"))) {
				return true;
			}
		} else if (cate.equals("여행")) {
			if (Objects.isNull(board_head) || (!board_head.equals("강원도") && !board_head.equals("서울")
					&& !board_head.equals("인천") && !board_head.equals("경기도") && !board_head.equals("충남")
					&& !board_head.equals("충북") && !board_head.equals("경북") && !board_head.equals("경남")
					&& !board_head.equals("대구") && !board_head.equals("대전") && !board_head.equals("세종")
					&& !board_head.equals("울산") && !board_head.equals("부산") && !board_head.equals("전북")
					&& !board_head.equals("전남") && !board_head.equals("광주") && !board_head.equals("제주도"))) {
				return true;
			}
		} else if (cate.equals("전체")) {
			if (Objects.isNull(board_head) || (!board_head.equals("자유") && !board_head.equals("여행"))) {
				return true;
			}
		} 

		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	// 전체 게시판 게시글 목록 조회
	// 페이지 번호에 따른 게시글 목록 조회
	public List<BoardReplyVO> selectEntire(int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from("
				+ "select c.*, n.notice_board_no from ("
				+ "select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0 and not board_cate = '공지') b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like) c left outer join notice n on (c.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null order by c.board_no desc" 
				+ "	)TMP" + ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startRow);
		ps.setInt(2, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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

	// 페이지에 따른 검색 목록 조회
	public List<BoardReplyVO> selectEntire(int startRow, int endRow, String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from(" 
				+ "select c.*, n.notice_board_no from ("
				+ "select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0 and instr(#1, ?) >0 and not board_cate = '공지' ) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like) c left outer join notice n on (c.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null order by c.board_no desc" 
				+ "	)TMP" + ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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

	// 전체 게시판 말머리 + 검색 게시글 조회
	public List<BoardReplyVO> selectEntire(int startRow, int endRow, String type, String key, String board_head)
			throws Exception {
		if (checkIsNotHead(board_head)) {
			return selectEntire(startRow, endRow, type, key);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from(" 
				+ "select c.*, n.notice_board_no from ("
				+ "select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0 and board_cate=? and instr(#1, ?) >0 ) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like) c left outer join notice n on (c.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null order by c.board_no desc" 
				+ "	)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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

	// 전체 게시판 only 말머리 목록 조회
	public List<BoardReplyVO> selectEntire(int startRow, int endRow, String board_head) throws Exception {
		if (checkIsNotHead(board_head)) {
			return selectEntire(startRow, endRow);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from(" 
				+ "select c.*, n.notice_board_no from ("
				+ "select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0 and board_cate=? ) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like) c left outer join notice n on (c.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null order by c.board_no desc" 
				+ "	)TMP" + ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, board_head);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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

	////////////////////////////////////////////////////////////////////////////////////////
	//전체 게시판
	//게시글 목록 개수
		public int selectEntireCount() throws Exception {
			Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

			String sql = "select count(board_no) from board where board_open = 0 and not board_cate='공지'";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			con.close();
			return count;
		}

	//검색 게시글 목록 개수
		public int selectEntireCount(String type, String key) throws Exception {
			Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

			String sql = "select count(board_no) from board " + "where board_open = 0 and instr(#1, ?)>0 and not board_cate='공지'";
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
		public int selectEntireCount(String type, String key, String board_head) throws Exception {
			if (checkIsNotHead(board_head)) {
				return selectEntireCount(type, key);
			}

			Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

			String sql = "select count(board_no) from board " + "where board_open = 0 and board_cate = ? and instr(#1, ?)>0";
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
		public int selectEntireCount(String board_head) throws Exception {
			if (checkIsNotHead(board_head)) {
				return selectEntireCount();
			}

			Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

			String sql = "select count(board_no) from board " + "where board_open = 0 and board_cate = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, board_head);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int count = rs.getInt(1);

			con.close();
			return count;
		}
	


////////////////////////////////////////////////////////////////////////////////////////////////////////

//자유, 여행 게시판		
		//게시글 목록 개수
				public int selectCount() throws Exception {
					Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

					String sql = "select count(board_no) from board where board_open = 0 and board_cate = ?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, cate);
					ResultSet rs = ps.executeQuery();
					rs.next();
					int count = rs.getInt(1);

					con.close();
					return count;
				}

			//검색 게시글 목록 개수
				public int selectCount(String type, String key) throws Exception {
					Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

					String sql = "select count(board_no) from board " + "where board_open = 0 and board_cate = ? and instr(#1, ?)>0";
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
				public int selectCount(String type, String key, String board_head) throws Exception {
					if (checkIsNotHead(board_head)) {
						return selectCount(type, key);
					}

					Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

					String sql = "select count(board_no) from board "
							+ "where board_open = 0 and board_cate = ? and board_head = ? and instr(#1, ?)>0";
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
				public int selectCount(String board_head) throws Exception {
					if (checkIsNotHead(board_head)) {
						return selectCount();
					}

					Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

					String sql = "select count(board_no) from board " + "where board_open = 0 and board_cate = ? and board_head=?";
					PreparedStatement ps = con.prepareStatement(sql);
					ps.setString(1, cate);
					ps.setString(2, board_head);
					ResultSet rs = ps.executeQuery();
					rs.next();
					int count = rs.getInt(1);

					con.close();
					return count;
				}


////////////////////////////////////////////////////////////////////////////////////////////////////


//페이지 번호에 따른 게시글 목록 조회
	public List<BoardReplyVO> selectByPage(int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from("
				+ "	select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0  and board_cate = ?) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "order by b.board_no desc" 
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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
	public List<BoardReplyVO> selectByPage(int startRow, int endRow, String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from(" 
				+ "	select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0  and board_cate = ? and instr(#1, ?) >0) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "order by b.board_no desc" 
				+ "	)TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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
	public List<BoardReplyVO> selectByPage(int startRow, int endRow, String type, String key, String board_head)
			throws Exception {
		if (checkIsNotHead(board_head)) {
			return selectByPage(startRow, endRow, type, key);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "select rownum rn, TMP.* from(" 
				+ "	select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0  and board_cate = ? and board_head = ? and instr(#1, ?) >0) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "order by b.board_no desc" 
				+ ")TMP"
				+ ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, board_head);
		ps.setString(3, key);
		ps.setInt(4, startRow);
		ps.setInt(5, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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
	public List<BoardReplyVO> selectByPage(int startRow, int endRow, String board_head) throws Exception {
		if (checkIsNotHead(board_head)) {
			return selectByPage(startRow, endRow);
		}

		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "	select rownum rn, TMP.* from(" 
				+ "	select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0  and board_cate = ? and board_head = ?) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "order by b.board_no desc" 
				+ "	)TMP"
				+ ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, board_head);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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


//상단고정 공지사항 조회 메소드
	public List<BoardDto> selectedNotice() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select b.* from board b inner join notice n on board_no = notice_board_no "
				+ "order by board_no desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		List<BoardDto> list = new ArrayList<>(); // 조회
		while (rs.next()) {
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
	public int selectedNoticeNum() throws Exception {
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
	public List<BoardReplyVO> selectNotice(int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "select rownum rn, TMP.* from(" 
				+ "select c.*, n.notice_board_no from ("
				+ "select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0  and board_cate = ? ) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like) c left outer join notice n on (c.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null order by c.board_no desc" 
				+ ")TMP" + ") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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
	public List<BoardReplyVO> selectNotice(int startRow, int endRow, String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from (" + "select rownum rn, TMP.* from(" 
				+ "select c.*, n.notice_board_no from ("
				+ "select count(r.reply_no) replynum, b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like "
				+ "from (select * from board where board_open = 0  and board_cate = ? and instr(#1, ?) >0) b left outer join reply r on(b.board_no = r.reply_board) "
				+ "group by b.board_no, b.board_nick, b.board_cate, b.board_head, b.board_title, b.board_date, b.board_view, b.board_like) c left outer join notice n on (c.board_no = n.notice_board_no) "
				+ "where n.notice_board_no is null order by c.board_no desc" 
				+ ")TMP" + ") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, cate);
		ps.setString(2, key);
		ps.setInt(3, startRow);
		ps.setInt(4, endRow);
		ResultSet rs = ps.executeQuery();

		List<BoardReplyVO> list = new ArrayList<>(); // 조회
		while (rs.next()) {
			BoardReplyVO dto = new BoardReplyVO();
			dto.setReplynum(rs.getInt("replynum"));
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
	public int selectNoticeCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from(" + "select b.board_no, n.notice_board_no from ("
				+ "select * from board where board_open = 0 and board_cate= ?) b left outer join notice n on (b.board_no = n.notice_board_no) "
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
	public int selectNoticeCount(String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select count(board_no) from(" + "select b.board_no, n.notice_board_no from ("
				+ "select * from board where board_open = 0 and board_cate= ? and instr(#1, ?) > 0) b left outer join notice n on (b.board_no = n.notice_board_no) "
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

////////////////////////////////////////////////////////////////////////////////////////

	
	// 등록 기능
	public void write(BoardDto dto) throws Exception {
		// 현재시간 YYYY-MM-DD HH.mm.ss
		// ex ) insert into board(~~~, board_date) values(to_char(sysdate, 'yyyy.mm.dd
		// hh24:mi:ss));
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "insert into board("
				+ "board_no, board_nick, board_cate, board_head, board_title, board_address, board_content"
				+ ") values(board_seq.nextval, ?,?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, dto.getBoard_nick());
		ps.setString(2, dto.getBoard_cate()); // 대분류
		ps.setString(3, dto.getBoard_head()); // 소분류
		ps.setString(4, dto.getBoard_title()); // 제목
		ps.setString(5, dto.getBoard_address()); // 기본주소
		ps.setString(6, dto.getBoard_content()); // 내용

		ps.execute();

		con.close();
	}

	

	// 상세보기
	public BoardDto find(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from board where board_no=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, board_no);
		ResultSet rs = ps.executeQuery();
		BoardDto dto;

		// 상세보기에 필요한 것 : 번호,작성자, 제목,주소(맵 표시용), 내용, 조회, 추천, 날짜 및 시간, 소분류, 답글 갯수(추후 추가)
		if (rs.next()) {
			dto = new BoardDto();

			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_cate(rs.getString("board_cate"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_address(rs.getString("board_address"));
			dto.setBoard_content(rs.getString("board_content"));
			dto.setBoard_view(rs.getInt("board_view"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_date(rs.getDate("board_date"));

		} else {
			dto = null;
		}

		con.close();

		return dto;
	}

	public boolean update(BoardDto dto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "update board "
				+ "set board_title=?, board_cate=?, board_head=?, board_address=?, board_content=? "
				+ "where board_no=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, dto.getBoard_title());
		ps.setString(2, dto.getBoard_cate());
		ps.setString(3, dto.getBoard_head());
		ps.setString(4, dto.getBoard_address());
		ps.setString(5, dto.getBoard_content());
		ps.setInt(6, dto.getBoard_no());

		int count = ps.executeUpdate();

		con.close();

		return count > 0;

	}

	// 시퀀스 번호
	public int getSequence() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select board_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		rs.next();
		int seq = rs.getInt(1);

		con.close();
		return seq;

	}

	// 번호까지 등록
	public void writeWithPrimaryKey(BoardDto boardDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "insert into board("
				+ "board_no, board_nick, board_cate, board_head, board_title, board_address, board_content"
				+ ") values(?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, boardDto.getBoard_no());
		ps.setString(2, boardDto.getBoard_nick());
		ps.setString(3, boardDto.getBoard_cate()); // 대분류
		ps.setString(4, boardDto.getBoard_head()); // 소분류
		ps.setString(5, boardDto.getBoard_title()); // 제목
		ps.setString(6, boardDto.getBoard_address()); // 기본주소
		ps.setString(7, boardDto.getBoard_content()); // 내용

		ps.execute();

		con.close();
	}

	// 조회수(수정해야됌)
	public void plusReadcount(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "update board set board_view=board_view+1 where board_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, board_no);

		ps.execute();

		con.close();
	}

	// 삭제
	public boolean delete(int board_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "delete board where board_no = ?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, board_no);
		int count = ps.executeUpdate();

		con.close();

		return count > 0;
	}

}
