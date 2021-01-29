package travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.beans.MemberDao;
import travel.beans.MemberDto;

@WebServlet(urlPatterns = "/member/memberLogin.do")
public class MemberLoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			1. 정보 수신
			req.setCharacterEncoding("UTF-8");
			MemberDto dto = new MemberDto();
			dto.setMember_id(req.getParameter("member_id"));
			dto.setMember_pw(req.getParameter("member_pw"));
			
//			2. 데이터베이스 조회
//			- 신규 메소드를 만드는 방법 : select * from member where member_id=? and member_pw=?
//			- 회원정보(MemberDto)를 이용해서 로그인 가능여부(boolean)을 알아낸다
			MemberDao dao = new MemberDao();
			boolean login = dao.login(dto);
			
//			3. 성공/실패에 따른 리다이렉트 처리
//			+ 추가 : 로그인 성공한 경우 세션에 check란 이름으로 사용자의 번호를 저장
//			+ 추가 : 로그인 성공한 경우 세션에 auth란 이름으로 사용자의 권한을 저장
			if(login) {
//				서블릿에서는 내장객체가 없어서 요청 객체인 req를 이용하여 세션에 접근하도록 설계되어 있다
				MemberDto m = dao.find(dto.getMember_id());//아이디로 회원정보 다 불러오기
				req.getSession().setAttribute("check", m.getMember_no());
				req.getSession().setAttribute("auth", m.getMember_auth());
				resp.sendRedirect("../index.jsp");//상대경로
//				resp.sendRedirect(req.getContextPath()+"/index.jsp");//절대경로
			}
			else { 
				resp.sendRedirect("memberLogin.jsp?error");//상대경로
//				resp.sendRedirect(req.getContextPath()+"/member/login.jsp");//절대경로
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}