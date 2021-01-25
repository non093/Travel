package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminDao;
import beans.AdminDto;
import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/login.do")
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
		
//		//관리자 로그인
//		AdminDao adminDao = new AdminDao();
//		AdminDto adminDto = new AdminDto();
//		adminDto.setAdmin_id(req.getParameter("member_id"));
//		adminDto.setAdmin_pw(req.getParameter("member_pw"));
//		
//		boolean adminLogin = adminDao.adminLogin(adminDto);
		
		if(login) {     
			MemberDto m = memberDao.find(memberDto.getMember_id());
			req.getSession().setAttribute("check", m.getMember_no());
			resp.sendRedirect("../index.jsp");
		}
//		else if(adminLogin) {
//			AdminDto admin = adminDao.adminFind(adminDto.getAdmin_id(), adminDto.getAdmin_pw());
//			req.getSession().setAttribute("auth", admin.getAdmin_id());
//			resp.sendRedirect("../index.jsp");
//
//		}
		else {
			resp.sendRedirect("login.jsp?error");
		}
	}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
