package travel.beans;

import java.sql.Date;

public class VisitCountDto {
	Date v_date;
	String session_id;
	
	
	public VisitCountDto() {
		super();
	}


	public Date getV_date() {
		return v_date;
	}


	public void setV_date(Date v_date) {
		this.v_date = v_date;
	}


	public String getSession_id() {
		return session_id;
	}


	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	
}
