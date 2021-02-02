package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDao;

@WebServlet(urlPatterns = "/member/find_pw.do")
public class Member_find_pw_Servlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		req.setCharacterEncoding("UTF-8");
		
		String member_id = req.getParameter("member_id");
		String member_email = req.getParameter("member_email");
		
		MemberDao memberDao = new MemberDao();
		String pw = memberDao.find_pw(member_id, member_email);
		
		if(pw!=null) {
			req.setAttribute("pw",pw);
			req.getRequestDispatcher("result_id&pw.jsp").forward(req, resp);
		}
		else {
			resp.sendRedirect("find_pw.jsp?error");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
