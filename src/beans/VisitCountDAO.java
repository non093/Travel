package beans;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JdbcUtil;

public class VisitCountDAO {
	
	public static final String USERNAME = "web";
	public static final String PASSWORD = "web";
	public String session;
	
	public boolean visitCheck(String session) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select COUNT(*) cnt from visit where session_id = ? and to_char(V_date, 'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD')";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, session);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int check = rs.getInt("cnt");
		
		boolean newVisit = true;
		
		if(check == 1) {
			newVisit = false;
		}
		
		con.close();
		return newVisit;
	}
	
	public void setVisitTotalCount(String session) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "INSERT INTO Visit (V_Date, Session_id) VALUES (sysdate, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, session);
		ps.execute();
		
		con.close();
	}
	
	public int getVisitTodayCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "SELECT COUNT(*) CNT from VISIT WHERE to_char(V_date, 'YYYY-MM-DD') = to_char(sysdate, 'YYYY-MM-DD')";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int todayCount = rs.getInt("CNT");
		
		con.close();
		return todayCount;
	}
	
	public int getVisitTotalCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "SELECT COUNT(*) CNT from VISIT";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		rs.next();
		int totalCount = rs.getInt("CNT");
		
		con.close();
		return totalCount;
	}
	
}


