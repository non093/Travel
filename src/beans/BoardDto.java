package beans;

import java.sql.Date;

public class BoardDto {
	private int board_no;
	private String board_nick;
	private String board_cate;
	private String board_head;
	private String board_title;
	private String board_address;
	private String board_content;
	private int board_view;
	private int board_like;
	private Date board_date;
	private int board_open;

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_nick() {
		return board_nick;
	}

	public void setBoard_nick(String board_nick) {
		this.board_nick = board_nick;
	}

	public String getBoard_cate() {
		return board_cate;
	}

	public void setBoard_cate(String board_cate) {
		this.board_cate = board_cate;
	}

	public String getBoard_head() {
		return board_head;
	}

	public void setBoard_head(String board_head) {
		this.board_head = board_head;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_address() {
		return board_address;
	}
	
	public void setBoard_address(String board_address) {
		this.board_address = board_address;
	}
	
	
	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public int getBoard_view() {
		return board_view;
	}

	public void setBoard_view(int board_view) {
		this.board_view = board_view;
	}

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}
	
	public boolean head(String board_head) {
		return this.board_head != null && this.board_head.equals(board_head);
	}
	public boolean cate(String board_cate) {
		return this.board_cate != null && this.board_cate.equals(board_cate);
	}
	public int getBoard_open() {
		return board_open;
	}


	public void setBoard_open(int board_open) {
		this.board_open = board_open;
	}
	public String getBoard_head_string() {
		if(this.board_head == null) 
			return "";
		else
			return this.board_head;
	}
	public String getBoard_cate_string() {
		if(this.board_cate == null) 
			return "";
		else
			return this.board_cate;
	}
	public BoardDto() {
		super();
	}
}
