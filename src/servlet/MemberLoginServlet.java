package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminDao;
import beans.AdminDto;
import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/memberLogin.do")
public class MemberLoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		req.setCharacterEncoding("UTF-8");
		
		MemberDto memberDto = new MemberDto();
		
		memberDto.setMember_id(req.getParameter("member_id"));
		memberDto.setMember_pw(req.getParameter("member_pw"));
		
		MemberDao memberDao = new MemberDao();
		boolean login = memberDao.login(memberDto);
		
		 
		
		if(login) {     
			HttpSession session = req.getSession( true );
	         
	         MemberDto m = memberDao.find(memberDto.getMember_id());
	         req.getSession().setAttribute("check", m.getMember_no());
	         session.setAttribute("nick", m.getMember_nick());
	         session.setMaxInactiveInterval( 60 * 120 /*2 시간*/);
	         
	         resp.sendRedirect("../index.jsp");

		}
		 
		else {
			resp.sendRedirect("memberLogin.jsp?error");
		}
	}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
