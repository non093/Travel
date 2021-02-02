package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.JdbcUtil;

public class ReplyDao {
	
	public static final String USERNAME = "web";
	public static final String PASSWORD = "web";
	
	//댓글 등록
	public void insert(ReplyDto replyDto) throws Exception {
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "insert into reply("
				+ "reply_no, reply_board, reply_nick, reply_content, reply_parent, reply_date)"
				+ " values(reply_seq.nextval, ?, ?, ?, ?, sysdate)";
		
		PreparedStatement ps =con.prepareStatement(sql);
		
		//ps.setInt(1, replyDto.getReply_no()); //댓글 번호
		ps.setInt(1, replyDto.getReply_board()); //게시글 번호
		ps.setString(2, replyDto.getReply_nick()); //댓글작성자
		ps.setString(3, replyDto.getReply_content()); //댓글내용
		ps.setInt(4, replyDto.getReply_parent()); //부모번호
		
		ps.execute();
		
		con.close();
		
		
	}
	 
	//댓글 목록
	public List<ReplyDto> select(int reply_board) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from reply where reply_board = ? order by decode(reply_parent, null, reply_no, reply_parent)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reply_board);
		ResultSet rs = ps.executeQuery();
		
		List<ReplyDto> list = new ArrayList<>();
		while(rs.next()) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setReply_no(rs.getInt("reply_no"));
			replyDto.setReply_board(rs.getInt("reply_board"));
			replyDto.setReply_nick(rs.getString("reply_nick"));
			replyDto.setReply_content(rs.getString("reply_content"));
			replyDto.setReply_parent(rs.getInt("reply_parent"));
			replyDto.setReply_date(rs.getDate("reply_date"));
			list.add(replyDto);
		}
		con.close();
		
		return list;
		
	}
	//댓글 삭제
	public boolean delete(int reply_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete from reply where reply_no"
				+ " in(select reply_no from reply start with reply_no = ?"
				+ " connect by prior reply_no = reply_parent)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reply_no);
		int count = ps.executeUpdate();
				
		con.close();
		
		return count > 0;
	}
	
	//댓글 수정
	public void update(ReplyDto replyDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update reply set reply_content = ? where reply_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, replyDto.getReply_content());
		ps.setInt(2, replyDto.getReply_no());
		ps.execute();
		
		con.close();
	}
	
	//댓글 시퀀스 번호
	public int getSequence() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql ="select reply_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		int seq = rs.getInt(1);
		
		con.close();
		return seq;
	}
	
	// 답변 : 댓글 1개의 정보를 가져온다
	public List<ReplyDto> review(int reply_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);

		String sql = "select * from reply where reply_no = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reply_no);
		ResultSet rs = ps.executeQuery();

		List<ReplyDto> list = new ArrayList<>();
		while (rs.next()) {
			ReplyDto replyDto = new ReplyDto();
			replyDto.setReply_no(rs.getInt("reply_no"));
			replyDto.setReply_board(rs.getInt("reply_board"));
			replyDto.setReply_nick(rs.getString("reply_nick"));
			replyDto.setReply_content(rs.getString("reply_content"));
			replyDto.setReply_parent(rs.getInt("reply_parent"));
			replyDto.setReply_date(rs.getDate("reply_date"));
			list.add(replyDto);
		}
		con.close();

		return list;

	}
}
