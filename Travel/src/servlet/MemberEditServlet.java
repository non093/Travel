package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/edit.do")
public class MemberEditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			
			MemberDto memberDto = new MemberDto();
			memberDto.setMember_no((int)req.getSession().getAttribute("check"));
			memberDto.setMember_nick(req.getParameter("member_nick"));
			memberDto.setMember_email(req.getParameter("member_email"));
			memberDto.setMember_pw(req.getParameter("member_pw"));
			
			MemberDao memberDao = new MemberDao();
			boolean result = memberDao.edit(memberDto);
			
			if(result) {
				resp.sendRedirect("my.jsp");

			}
			else {
				resp.sendRedirect("edit.jsp?error");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
