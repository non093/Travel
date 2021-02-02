package beans;

import java.sql.Date;

public class ReplyDto {
	private int reply_no; //댓글번호
	private int reply_board; //게시글번호(board_no 외래키)
	private String reply_nick; //작성자(member_nick 외래키)
	private int reply_parent; //부모글
	private String reply_content; //댓글내용
	private Date reply_date; //댓글등록일
	
	
	public ReplyDto() {
		super();
	}

	public int getReply_no() {
		return reply_no;
	}

	public void setReply_no(int reply_no) {
		this.reply_no = reply_no;
	}

	public int getReply_board() {
		return reply_board;
	}

	public void setReply_board(int reply_board) {
		this.reply_board = reply_board;
	}

	public String getReply_nick() {
		return reply_nick;
	}

	public void setReply_nick(String reply_nick) {
		this.reply_nick = reply_nick;
	}

	public int getReply_parent() {
		return reply_parent;
	}

	public void setReply_parent(int reply_parent) {
		this.reply_parent = reply_parent;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Date getReply_date() {
		return reply_date;
	}

	public void setReply_date(Date reply_date) {
		this.reply_date = reply_date;
	}
	
	
}
