package travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.beans.ReportDao;

@WebServlet(urlPatterns = "/admin/reportDelete.do")
public class ReportDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int report_no = Integer.parseInt(req.getParameter("report_no"));
			
			ReportDao adminBoardDao = new ReportDao();
			boolean result = adminBoardDao.reportDelete(report_no);
			
			if(result) {
				resp.sendRedirect("reportList.jsp");
			}
			else {
				resp.sendError(404);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
