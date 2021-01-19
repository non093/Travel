package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import travel.utl.JdbcUtil;

public class AdminDao {
	public static final String USERNAME = "semi_project";
	public static final String PASSWORD = "semi_project";
	
	public AdminDto find(String admin_id) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from admin where admin_id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, admin_id);
		ResultSet rs = ps.executeQuery();
		
		AdminDto adminDto;
		if(rs.next()) {
			adminDto = new AdminDto();
			adminDto.setAdmin_no(rs.getInt("admin_no"));
			adminDto.setAdmin_id(rs.getString("admin_id"));
			adminDto.setAdmin_pw(rs.getString("admin_pw"));
			adminDto.setAdmin_nick(rs.getString("admin_nick"));
			adminDto.setAdmin_auth(rs.getString("admin_auth"));
			
		}
		else {
			adminDto = null;
		}
		
		con.close();
		
		return adminDto;		
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
			
			
		}
		else {
			memberDto = null;
		}
		
		con.close();
		
		return memberDto;
	}
	
	
	public boolean login(AdminDto adminDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from admin where admin_id=? and admin_pw=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, adminDto.getAdmin_id());
		ps.setString(2, adminDto.getAdmin_pw());
		ResultSet rs = ps.executeQuery();
		
		boolean result = rs.next();

		con.close();
		
		return result;
	}
	public boolean editByAdmin(MemberDto memberDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update member set member_image=? member_nick=?, member_email=? where member_no";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setBlob(1, memberDto.getMember_image());
		ps.setString(2, memberDto.getMember_nick());
		ps.setString(3, memberDto.getMember_email());
		ps.setInt(4, memberDto.getMember_no());
		
		int count = ps.executeUpdate();
		
		con.close();
		return count > 0;
	}
	public boolean delete(int member_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete member where member_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count > 0;
		
	}
}
