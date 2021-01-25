package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/find_id.do")
public class Member_Find_id_Servlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		req.setCharacterEncoding("UTF-8");
		
		String member_email = req.getParameter("member_email");
		String member_pw = req.getParameter("member_pw");
		
		
		MemberDao memberDao = new MemberDao();
		String id = memberDao.find_id(member_email, member_pw);
		if(id!=null) { 
			req.setAttribute("id", id);
			req.getRequestDispatcher("result_id&pw.jsp").forward(req, resp);
		}
		else {
			resp.sendRedirect("find_id.jsp?error");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
		
	}
}
