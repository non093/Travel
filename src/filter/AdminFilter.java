package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(urlPatterns = {
		"/admin/adminEditMember.jsp", "/admin/adminMemberList.jsp",
		"/admin/adminPage.jsp", "/admin/adminMemberProfile.jsp",
		"/admin/boardList.jsp", "/admin/reportEdit.jsp"
	})
public class AdminFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//필터에서는 HTTP요청을 처리하고 싶으면 request, response를 각각 다운캐스팅해서 사용해야 한다.
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		//관리자 검사
		String auth = (String)req.getSession().getAttribute("auth");
		boolean isAdmin = auth != null && auth.equals("관리자");
		
		//보내주는 방법(통과 명령)
		if(isAdmin) {
			chain.doFilter(request, response);
		}
		//관리자가 아닌 사람들에 대한 처리
		//= 다른 페이지로 재접속 요청 : resp.sendRedirect("페이지주소");
		//= 에러 메세지 전송 : resp.sendError(상태번호);
		else {
			//resp.sendError(403);
			resp.sendRedirect("../index.jsp");
		}
	}
}