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
		"/member/detail.jsp",
		"/member/edit.jsp", "/member/editPW.jsp",
		"/member/upload.jsp"
})
public class MemberFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		boolean isLogin = req.getSession().getAttribute("check")!=null;
		
		if(isLogin) {
			chain.doFilter(request, response);
		}
		else {
			resp.sendRedirect(req.getContextPath()+"/member/login.jsp");
		}
	}

}
