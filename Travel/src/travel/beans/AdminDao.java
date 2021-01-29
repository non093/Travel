package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import travel.admin.util.JdbcUtil;

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
	
	public AdminDto find(int admin_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from admin where admin_no = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, admin_no);
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
	
	public MemberDto memberSearch(int member_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_no=?";
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
	public boolean memberEditByAdmin(MemberDto memberDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update member set member_nick=?, member_email=?, member_auth=? where member_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, memberDto.getMember_nick());
		ps.setString(2, memberDto.getMember_email());
		ps.setString(3, memberDto.getMember_auth());
		ps.setInt(4, memberDto.getMember_no());
		
		int count = ps.executeUpdate();
		
		con.close();
		return count > 0;
	}
	public boolean memberDelete(int member_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete member where member_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count > 0;
		
	}
	

	//검색 : keyword를 이용한 아이디 "시작검사"
	public List<MemberDto> memberSelect(String keyword) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
//		String sql = "select * from member where member_id like ?||'%' order by member_id asc";
		String sql = "select * from member where instr(member_id, ?) = 1 order by member_id asc";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> list = new ArrayList<>();
		while(rs.next()) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMember_no(rs.getInt("member_no"));
			memberDto.setMember_id(rs.getString("member_id"));
			memberDto.setMember_pw(rs.getString("member_pw"));
			memberDto.setMember_nick(rs.getString("member_nick"));
			memberDto.setMember_email(rs.getString("member_email"));
			memberDto.setMember_date(rs.getDate("member_date"));
			memberDto.setMember_auth(rs.getString("member_auth"));
			
			
			list.add(memberDto);
		}
		
		con.close();
		
		return list;
	}
	
	public List<MemberDto> memberSelect(String type, String keyword) throws Exception {
//		분류나 검색어 중 하나라도 없으면 null 반환
		if(type == null || keyword == null) return null;
		
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where instr(#1, ?) > 0 order by member_id asc";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, keyword);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> list = new ArrayList<>();
		while(rs.next()) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMember_no(rs.getInt("member_no"));
			memberDto.setMember_id(rs.getString("member_id"));
			memberDto.setMember_pw(rs.getString("member_pw"));
			memberDto.setMember_nick(rs.getString("member_nick"));
			memberDto.setMember_email(rs.getString("member_email"));
			memberDto.setMember_date(rs.getDate("member_date"));
			memberDto.setMember_auth(rs.getString("member_auth"));
			
			list.add(memberDto);
		}
		
		con.close();
		
		return list;
	}
	public int getMemberSearchCount(String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(*) from member where instr(#1, ?) > 0";
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
	
	
	public int getMemberCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(*) from member";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
//		int count = rs.getInt("count(*)");
		con.close();
		
		return count;
	}
	public List<MemberDto> memberSearchList(String type, String key, int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = 	"select * from(" + 
							"select rownum rn, TMP.* from(" + 
								"select * from member "
								+ "where instr(#1, ?) > 0 "
								+ "order by member_no desc" + 
							")TMP" + 
						") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> memberList = new ArrayList<>();
		while(rs.next()) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMember_no(rs.getInt("member_no"));
			memberDto.setMember_id(rs.getString("member_id"));
			memberDto.setMember_pw(rs.getString("member_pw"));
			memberDto.setMember_nick(rs.getString("member_nick"));
			memberDto.setMember_email(rs.getString("member_email"));
			memberDto.setMember_date(rs.getDate("member_date"));
			memberDto.setMember_auth(rs.getString("member_auth"));
			memberList.add(memberDto);
		}
		
		con.close();
		
		return memberList;
	}
	
	
	public List<MemberDto> memberPagingList(int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = 	"select * from(" + 
							"select rownum rn, TMP.* from(" + 
								"select * from member order by member_no desc" + 
							")TMP" + 
						") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startRow);
		ps.setInt(2, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<MemberDto> memberList = new ArrayList<>();
		while(rs.next()) {
			MemberDto memberDto = new MemberDto();
			memberDto.setMember_no(rs.getInt("member_no"));
			memberDto.setMember_id(rs.getString("member_id"));
			memberDto.setMember_pw(rs.getString("member_pw"));
			memberDto.setMember_nick(rs.getString("member_nick"));
			memberDto.setMember_email(rs.getString("member_email"));
			memberDto.setMember_date(rs.getDate("member_date"));
			memberDto.setMember_auth(rs.getString("member_auth"));			
			memberList.add(memberDto);
		}
		con.close();
		
		return memberList;
	}

}
