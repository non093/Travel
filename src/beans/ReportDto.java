package beans;

import java.sql.Date;

public class ReportDto {
	private int report_no;
	private String report_nick;
	private String report_header;
	private String report_title;
	private String report_content;
	private String report_answer;
	private Date report_date;
	private int report_qa;
	
	
	public ReportDto() {
		super();
	}

	public int getReport_no() {
		return report_no;
	}

	public void setReport_no(int report_no) {
		this.report_no = report_no;
	}

	public String getReport_nick() {
		return report_nick;
	}

	public void setReport_nick(String report_nick) {
		this.report_nick = report_nick;
	}
	
	public String getReport_header() {
		return report_header;
	}

	public void setReport_header(String report_header) {
		this.report_header = report_header;
	}

	public String getReport_title() {
		return report_title;
	}

	public void setReport_title(String report_title) {
		this.report_title = report_title;
	}

	public String getReport_content() {
		return report_content;
	}

	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}

	public Date getReport_date() {
		return report_date;
	}

	public void setReport_date(Date report_date) {
		this.report_date = report_date;
	}

	public int getReport_qa() {
		return report_qa;
	}

	public void setReport_qa(int report_qa) {
		this.report_qa = report_qa;
	}

	public String getReport_answer() {
		return report_answer;
	}

	public void setReport_answer(String report_answer) {
		this.report_answer = report_answer;
	}
	
	
}
