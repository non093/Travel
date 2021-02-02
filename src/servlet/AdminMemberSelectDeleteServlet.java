package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminDao;

@WebServlet(urlPatterns = "/admin/adminMemberSelectDelete.do")
public class AdminMemberSelectDeleteServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			String[]checked = req.getParameterValues("checked");
			
			AdminDao adminDao = new AdminDao();
			
			for(int i=0; i<checked.length; i++) {
				
				int del = Integer.parseInt(checked[i]);
							
				boolean result = adminDao.memberDelete(del);
				
				if(result) {
					del++;
					
				}
				else {
					resp.sendError(404);
				}
				
			}
			resp.sendRedirect("adminMemberList.jsp");
		
			
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
