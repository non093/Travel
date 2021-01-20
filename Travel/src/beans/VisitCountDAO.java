package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.naming.NamingException;
 
import util.DBConnection;
 
public class VisitCountDAO 
{
    private static VisitCountDAO instance;
    
    private VisitCountDAO(){}
    public static VisitCountDAO getInstance(){
        if(instance==null)
            instance=new VisitCountDAO();
        return instance;
    }
    
    public void setTotalCount() throws SQLException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            
            StringBuffer sql = new StringBuffer();
            sql.append("INSERT INTO VISIT (V_DATE) VALUES (sysdate)");
            
            conn = DBConnection.getConnection();
                        
            conn.setAutoCommit(false);
            
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate();
            conn.commit(); 
            
        } catch (ClassNotFoundException | NamingException | SQLException sqle) {
            conn.rollback(); 
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    
    public int getTotalCount()
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int totalCount = 0;
        
        try {
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT COUNT(*) AS TotalCnt FROM VISIT");
            
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            
            if (rs.next()) 
                totalCount = rs.getInt("TotalCnt");
            
            return totalCount;
            
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    public int getTodayCount()
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int todayCount = 0;
        
        try {
            
            StringBuffer sql = new StringBuffer();
            sql.append("SELECT COUNT(*) AS TodayCnt FROM VISIT");
            sql.append(" WHERE TO_DATE(V_DATE, 'YYYY-MM-DD') = TO_DATE(sysdate, 'YYYY-MM-DD')");
            
            conn = DBConnection.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery();
            
            if (rs.next()) 
                todayCount = rs.getInt("TodayCnt");
            
            return todayCount;
            
        } catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}