package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import travel.admin.util.JdbcUtil;

public class MemberDao {
	public static final String USERNAME = "semi_project";
	public static final String PASSWORD = "semi_project"; 
	
	public boolean login(MemberDto dto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_id=? and member_pw=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, dto.getMember_id());
		ps.setString(2, dto.getMember_pw());
		ResultSet rs = ps.executeQuery();//데이터는 많아야 1개
		
		boolean result = rs.next();//있어요?

		
		con.close();
		
		return result;
	}
	
	public MemberDto find(String member_id) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member_id);
		ResultSet rs = ps.executeQuery();
		
		MemberDto memberDto;
		if(rs.next()) {
			memberDto = new MemberDto();
			memberDto.setMember_no(rs.getInt("member_no"));
			memberDto.setMember_id(rs.getString("member_id"));
			memberDto.setMember_pw(rs.getString("member_pw"));
			memberDto.setMember_nick(rs.getString("member_nick"));
			memberDto.setMember_email(rs.getString("member_email"));
			memberDto.setMember_date(rs.getDate("member_date"));
			memberDto.setMember_auth(rs.getString("member_auth"));
			
		}
		else {
			memberDto = null;
		}
		
		con.close();
		
		return memberDto;		
	}
	public MemberDto find(int member_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		ResultSet rs = ps.executeQuery();
		
		MemberDto memberDto;
		if(rs.next()) {
			memberDto = new MemberDto();
			memberDto.setMember_no(rs.getInt("member_no"));
			memberDto.setMember_id(rs.getString("member_id"));
			memberDto.setMember_pw(rs.getString("member_pw"));
			memberDto.setMember_nick(rs.getString("member_nick"));
			memberDto.setMember_email(rs.getString("member_email"));
			memberDto.setMember_date(rs.getDate("member_date"));
			memberDto.setMember_auth(rs.getString("member_auth"));
		}
		else {
			memberDto = null;
		}
		
		con.close();
		
		return memberDto;
	}
}
