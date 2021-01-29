package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.MemberDto;
import util.JdbcUtil;

public class MemberDao {
	
	public static final String USERNAME = "web";
	public static final String PASSWORD = "web";
	
	public MemberDto find(int member_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		ResultSet rs = ps.executeQuery();
		
		MemberDto dto;
		if(rs.next()) {
			dto = new MemberDto();
			dto.setMember_no(rs.getInt("member_no"));
			dto.setMember_id(rs.getString("member_id"));
			dto.setMember_pw(rs.getString("member_pw"));
			dto.setMember_nick(rs.getString("member_nick"));
			dto.setMember_birth(rs.getString("member_birth"));
			dto.setMember_point(rs.getInt("member_point"));
			dto.setMember_auth(rs.getString("member_auth"));
			dto.setMember_join(rs.getDate("member_join"));
		}
		else {
			dto = null;
		}
		
		con.close();
		
		return dto;
	}
}
