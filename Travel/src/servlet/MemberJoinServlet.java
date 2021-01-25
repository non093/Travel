package servlet;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/join.do")
public class MemberJoinServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

		req.setCharacterEncoding("UTF-8");
		
		MemberDto memberDto = new MemberDto();
		MemberDao memberDao = new MemberDao();

		memberDto.setMember_id(req.getParameter("member_id"));
		memberDto.setMember_pw(req.getParameter("member_pw"));
		memberDto.setMember_nick(req.getParameter("member_nick"));
		memberDto.setMember_email(req.getParameter("member_email"));

		memberDao.insert(memberDto);
		resp.sendRedirect("JoinSuccess.jsp");//회원 가입 성공시
		
		}
		
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
			
		}

	}
}	
