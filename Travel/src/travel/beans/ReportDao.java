package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import travel.util.JdbcUtil;

public class ReportDao {
	
	public static final String USERNAME = "semi_project";
	public static final String PASSWORD = "semi_project";
	
	public int getReportSearchCount(String type, String key) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(*) from report where instr(#1, ?) > 0";
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
	
	
	public int getReportCount() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select count(*) from report";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
//		int count = rs.getInt("count(*)");
		con.close();
		
		return count;
	}
	public List<ReportDto> reportSearchList(String type, String key, int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = 	"select * from(" + 
							"select rownum rn, TMP.* from(" + 
								"select * from report "
								+ "where instr(#1, ?) > 0 "
								+ "order by report_no desc" + 
							")TMP" + 
						") where rn between ? and ?";
		sql = sql.replace("#1", type);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, key);
		ps.setInt(2, startRow);
		ps.setInt(3, endRow);
		ResultSet rs = ps.executeQuery();
		
		List<ReportDto> reportList = new ArrayList<>();
		while(rs.next()) {
			ReportDto reportDto = new ReportDto();
			reportDto.setReport_no(rs.getInt("report_no"));
			reportDto.setReport_nick(rs.getString("report_nick"));
			reportDto.setReport_header(rs.getString("report_header"));
			reportDto.setReport_title(rs.getString("report_title"));
			reportDto.setReport_content(rs.getString("report_content"));
			reportDto.setReport_answer(rs.getString("report_answer"));
			reportDto.setReport_date(rs.getDate("report_date"));
			reportDto.setReport_qa(rs.getInt("report_qa"));
						
			reportList.add(reportDto);
		}
		
		con.close();
		
		return reportList;
	}
	
	
	public List<ReportDto> reportPagingList(int startRow, int endRow) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = 	"select * from(" + 
							"select rownum rn, TMP.* from(" + 
								"select * from report order by report_no desc" + 
							")TMP" + 
						") where rn between ? and ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, startRow);
		ps.setInt(2, endRow);
		ResultSet rs = ps.executeQuery();
		

		List<ReportDto> reportList = new ArrayList<>();
		while(rs.next()) {
			ReportDto reportDto = new ReportDto();
			reportDto.setReport_no(rs.getInt("report_no"));
			reportDto.setReport_nick(rs.getString("report_nick"));
			reportDto.setReport_header(rs.getString("report_header"));
			reportDto.setReport_title(rs.getString("report_title"));
			reportDto.setReport_content(rs.getString("report_content"));
			reportDto.setReport_answer(rs.getString("report_answer"));
			reportDto.setReport_date(rs.getDate("report_date"));
			reportDto.setReport_qa(rs.getInt("report_qa"));
			
			reportList.add(reportDto);
		}
		con.close();
		
		return reportList;
	}
	
	public ReportDto find(int report_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from report where report_no = ?";//결과가 절대로 여러개가 나올 수 없다
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, report_no);
		ResultSet rs = ps.executeQuery();
		
		//rs는 예상 결과가 1개 아니면 0개. 즉 있냐 없냐만 알면 된다.
		//목록처럼 List를 만들어서 add를 할 필요가 없다(while문이 필요 없다)
		ReportDto reportDto;
		if(rs.next()) {//결과가 있다면 객체를 만들어 데이터베이스 값을 전부다 복사하겠다
			reportDto = new ReportDto();
			reportDto.setReport_no(rs.getInt("report_no"));
			reportDto.setReport_nick(rs.getString("report_nick"));
			reportDto.setReport_header(rs.getString("report_header"));
			reportDto.setReport_title(rs.getString("report_title"));
			reportDto.setReport_content(rs.getString("report_content"));
			reportDto.setReport_answer(rs.getString("report_answer"));
			reportDto.setReport_date(rs.getDate("report_date"));
			reportDto.setReport_qa(rs.getInt("report_qa"));
		}
		else {//결과가 없다면 잘못된 번호니까 null이라는 값을 반환하겠다
			reportDto = null;
		}
		
		con.close();
		
		return reportDto;
	}
	
	public boolean update(ReportDto dto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "update report "
						+ "set report_header=?, report_title=?, report_content=?, report_answer=?, report_qa=? "
						+ "where report_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setString(1, dto.getReport_header());
		ps.setString(2, dto.getReport_title());
		ps.setString(3, dto.getReport_content());
		ps.setString(4, dto.getReport_answer());
		ps.setInt(5, dto.getReport_qa());
		ps.setInt(6, dto.getReport_no());
		
		int count = ps.executeUpdate();
		
		con.close();
		return count > 0;
//		if(count > 0) {
//			return true;
//		}
//		else {
//			return false;
//		}
	}
	
	public int getSequence() throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select report_seq.nextval from dual";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();//무조건 나옴
		int seq = rs.getInt(1);
//		int seq = rs.getInt("NEXTVAL");
		
		con.close();
		return seq;
	}
	public void boardWithPrimaryKey(ReportDto reportDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "insert into report values(?, ?, ?, ?, ?, '', sysdate, 0)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reportDto.getReport_no());
		ps.setString(2, reportDto.getReport_nick());
		ps.setString(3, reportDto.getReport_header());
		ps.setString(4, reportDto.getReport_title());
		ps.setString(5, reportDto.getReport_content());
		
		ps.execute();
		
		con.close();
	}
	
	public void writeWithPrimaryKey(ReportDto reportDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "insert into report values(?, ?,'일반', ?, ?, '', sysdate, 0)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, reportDto.getReport_no());
		ps.setString(2, reportDto.getReport_nick());
		ps.setString(3, reportDto.getReport_title());
		ps.setString(4, reportDto.getReport_content());
		
		ps.execute();
		
		con.close();
	}
	
	public boolean reportDelete(int report_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete report where report_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, report_no);
		int count = ps.executeUpdate();
		
		con.close();
		
		return count > 0;
		
	}
	
	public ReportDto ownerFind(String report_nick) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from report where report_nick = ?";//결과가 절대로 여러개가 나올 수 없다
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, report_nick);
		ResultSet rs = ps.executeQuery();
		
		//rs는 예상 결과가 1개 아니면 0개. 즉 있냐 없냐만 알면 된다.
		//목록처럼 List를 만들어서 add를 할 필요가 없다(while문이 필요 없다)
		ReportDto reportDto;
		if(rs.next()) {//결과가 있다면 객체를 만들어 데이터베이스 값을 전부다 복사하겠다
			reportDto = new ReportDto();
			reportDto.setReport_no(rs.getInt("report_no"));
			reportDto.setReport_nick(rs.getString("report_nick"));
			reportDto.setReport_header(rs.getString("report_header"));
			reportDto.setReport_title(rs.getString("report_title"));
			reportDto.setReport_content(rs.getString("report_content"));
			reportDto.setReport_answer(rs.getString("report_answer"));
			reportDto.setReport_date(rs.getDate("report_date"));
			reportDto.setReport_qa(rs.getInt("report_qa"));
		}
		else {//결과가 없다면 잘못된 번호니까 null이라는 값을 반환하겠다
			reportDto = null;
		}
		
		con.close();
		
		return reportDto;
	}

}
