package beans;

import java.sql.Date;

public class MemberDto {
	private int member_no;
	private String member_id;
	private String member_pw;
	private String member_nick;
	private String member_birth;
	private String member_auth;
	private int member_point;
	private Date member_join;
	public MemberDto() {
		super();
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_nick() {
		return member_nick;
	}
	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}
	public String getMember_birth() {
		return member_birth;
	}
	public void setMember_birth(String member_birth) {
		this.member_birth = member_birth;
	}
	public String getMember_auth() {
		return member_auth;
	}
	public void setMember_auth(String member_auth) {
		this.member_auth = member_auth;
	}
	public int getMember_point() {
		return member_point;
	}
	public void setMember_point(int member_point) {
		this.member_point = member_point;
	}
	public Date getMember_join() {
		return member_join;
	}
	public void setMember_join(Date member_join) {
		this.member_join = member_join;
	}
//	객체의 권한 검사
	public boolean is(String member_auth) {
		return this.member_auth != null && this.member_auth.equals(member_auth);
	}
}
