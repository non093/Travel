package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminDao;

@WebServlet(urlPatterns = "/admin/adminDelete.do")
public class AdminDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int member_no = Integer.parseInt(req.getParameter("member_no"));
			
			AdminDao adminDao = new AdminDao();
			boolean result = adminDao.memberDelete(member_no);
			
			if(result) {
				resp.sendRedirect("adminMemberList.jsp");
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
