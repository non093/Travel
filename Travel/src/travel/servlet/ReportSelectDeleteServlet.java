package travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.beans.ReportDao;

@WebServlet(urlPatterns = "/admin/ReportDelete.do")
public class ReportSelectDeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String[]checked = req.getParameterValues("checked");
			
			ReportDao reportDao = new ReportDao();
			
			for(int i=0; i<checked.length; i++) {
				
				int del = Integer.parseInt(checked[i]);
							
				boolean result = reportDao.reportDelete(del);
				
				if(result) {
					del++;
					
				}
				else {
					resp.sendError(404);
				}
				
			}
			resp.sendRedirect("reportList.jsp");
		
			
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
