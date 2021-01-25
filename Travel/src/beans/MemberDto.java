package beans;

import java.sql.Date;

public class MemberDto {
	private int member_no;
	private String member_pw;
	private String member_id;
	private String member_nick;
	private String member_email;
	private Date member_date;  
	
	public MemberDto() {
		super();
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getMember_pw() {
		return member_pw;
	}

	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_nick() {
		return member_nick;
	}

	public void setMember_nick(String member_nick) {
		this.member_nick = member_nick;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public Date getMember_date() {
		return member_date;
	}

	public void setMember_date(Date member_date) {
		this.member_date = member_date;
	}
	
	
	
}
