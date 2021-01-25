package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/editPW.do")
public class MemberEditPWServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			
			int member_no = (int)req.getSession().getAttribute("check");
			String origin_pw = req.getParameter("origin_pw");
			String change_pw = req.getParameter("change_pw");
			
			
			MemberDto memberDto = new MemberDto();
			
			MemberDao memberDao  = new MemberDao();
			boolean result = memberDao.editPW(member_no, origin_pw, change_pw);
			
			if(result) {
			resp.sendRedirect("my.jsp");
			}
			else {
				resp.sendRedirect("editPW.jsp?error");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
