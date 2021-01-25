package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDao {
	public static final String USERNAME = "semi";
	public static final String PASSWORD = "semi";
	 
	
	//로그인
	public boolean adminLogin(AdminDto adminDto)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from admin where admin_id =? and admin_pw =?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, adminDto.getAdmin_id());
		ps.setString(2, adminDto.getAdmin_pw());
		ResultSet rs = ps.executeQuery();
		
		boolean result = rs.next();
		
		con.close();
		
		return result;
		
	}
	public AdminDto adminFind(String admin_id, String admin_pw)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from admin where admin_id = ? and admin_pw = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, admin_id);
		ps.setString(2, admin_pw);
		
		ResultSet rs = ps.executeQuery();
		
		AdminDto adminDto;
		if(rs.next()) {
			adminDto = new AdminDto();
			adminDto.setAdmin_id(rs.getString("admin_id"));
			adminDto.setAdmin_pw(rs.getString("admin_pw"));
		}
		else {
			adminDto = null;
		}
		
		con.close(); 
		
		return adminDto;	
		
	}
}
