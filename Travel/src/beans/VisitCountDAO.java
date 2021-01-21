package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JdbcUtil;

public class VisitCountDAO {
	
	public static final String USERNAME = "semi";
	public static final String PASSWORD = "semi";
	
	public void setVisitTotalCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "INSERT INTO Visit (V_Date) VALUES (sysdate)";
		PreparedStatement ps = con.prepareStatement(sql);
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


