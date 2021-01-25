package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.sun.crypto.provider.RSACipher;

import beans.MemberDto;

public class MemberDao {
	public static final String USERNAME = "semi";
	public static final String PASSWORD = "semi";
	
	
	//회원 가입
	public void insert(MemberDto memberDto)throws Exception{
		Connection con  = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "insert into member values(member_seq.nextval, ?, ?, ?, ?,sysdate)";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, memberDto.getMember_id());
		ps.setString(2, memberDto.getMember_pw());
		ps.setString(3, memberDto.getMember_nick());
		ps.setString(4, memberDto.getMember_email());
		ps.execute();
		ps.close();
		
	}
	
	//로그인
	public boolean login(MemberDto memberDto)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_id =? and member_pw =?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, memberDto.getMember_id());
		ps.setString(2, memberDto.getMember_pw());
		ResultSet rs = ps.executeQuery();
		
		boolean result = rs.next();
		
		con.close();
		
		return result;
		
	}
	
	//로그인성시, 세션에 저장하기 위해 데이터 불러옴
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
			
		}
		else {
			memberDto = null;
		}
		
		con.close(); 
		
		return memberDto;		
	}
	
	//회원정보 수정
	public boolean edit(MemberDto memberDto) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update member set member_nick = ?, member_email = ? where member_no = ? and member_pw = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, memberDto.getMember_nick());
		ps.setString(2, memberDto.getMember_email());
		ps.setInt(3, memberDto.getMember_no());
		ps.setString(4, memberDto.getMember_pw());
		int count = ps.executeUpdate();
		
		con.close();
		
		return count>0;
		
	}
	//회원 탈퇴
	public boolean delete(int member_no)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete member where member_no = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count>0;
	}
	
	//비밀번호 수정
	public boolean editPW(int member_no, String origin_pw, String change_pw)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update member set member_pw = ? where member_no = ? and member_pw=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, change_pw);
		ps.setInt(2, member_no);
		ps.setString(3, origin_pw);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count>0;
		
	}
	
	//아이디 찾기
	public String find_id(String member_id, String member_pw) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select member_id from member where member_email=? and member_pw=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member_id);
		ps.setString(2, member_pw);
		ResultSet rs = ps.executeQuery();
		
		String id;
		if(rs.next()) {
			id = rs.getString("member_id");
		}
		else {
			id = null;
		}
		con.close();
		return id;
		
	}

	//비밀번호 찾기
	public String find_pw(String member_id, String member_email) throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select member_pw from member where member_id = ? and member_email=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member_id);
		ps.setString(2, member_email);
		ResultSet rs = ps.executeQuery();
		
		String pw;
		if(rs.next()) {
			pw = rs.getString("member_pw");
		}
		else {
			pw = null;
		}
		
		con.close();
		return pw;
	}
	//아이디 중복 체크
	public boolean id_check(String member_id)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_id=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member_id);
		ResultSet rs = ps.executeQuery();
		
		boolean result;
		if(rs.next()) {//중복된 아이디면
			result = true;
		}
		else {
			result=false;
		}
		con.close();
		
		return result;
	}
	
	//닉네임 중복 체크
	public boolean nick_check(String member_nick)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from member where member_nick=?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, member_nick);
		ResultSet rs = ps.executeQuery();
		
		boolean result;
		if(rs.next()) {
			result = true;
		}
		else {
			result=false;
		}
		con.close();
		
		return result;
	}
}
