package travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.beans.AdminDao;
import travel.beans.MemberDto;

@WebServlet(urlPatterns = "/admin/adminEdit.do")
public class AdminEditServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setCharacterEncoding("UTF-8");
			MemberDto memberDto = new MemberDto();

			memberDto.setMember_no(Integer.parseInt(req.getParameter("member_no")));
			memberDto.setMember_nick(req.getParameter("member_nick"));
			memberDto.setMember_email(req.getParameter("member_email"));
			memberDto.setMember_auth(req.getParameter("member_auth"));
			
			AdminDao adminDao = new AdminDao();
			
			boolean result = adminDao.memberEditByAdmin(memberDto);
			
			if(result) {
				resp.sendRedirect("adminMemberProfile.jsp?member_no="+memberDto.getMember_no());
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
