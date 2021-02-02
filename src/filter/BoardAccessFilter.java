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

import beans.BoardDao;
import beans.BoardDto;
import beans.MemberDao;
import beans.MemberDto;

@WebFilter(urlPatterns = {
		"/board/edit.jsp", "/board/edit.do",
		"/board/delete.do"
		
})
public class BoardAccessFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		req.setCharacterEncoding("UTF-8");
		HttpServletResponse resp = (HttpServletResponse)response;
		
		try {
			
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			BoardDao boardDao = new BoardDao();
			BoardDto boardDto = boardDao.find(board_no);
			
			int member_no = (int)req.getSession().getAttribute("check");
			MemberDao memberDao = new MemberDao();
			MemberDto memberDto = memberDao.find(member_no);
			
			if(memberDto.getMember_nick().equals(boardDto.getBoard_nick())) {
				chain.doFilter(request, response);
				return;
			}
			
			resp.sendError(403);
					
		}
		catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
