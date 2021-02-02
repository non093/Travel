package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.MemberDao;
import beans.MemberDto;
import beans.ReplyDao;
import beans.ReplyDto;

@WebServlet(urlPatterns = "/board/reply_insert.do")
public class ReplyInsertServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println( "test" );
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			req.setCharacterEncoding("UTF-8");
			ReplyDto replyDto = new ReplyDto();
			
			replyDto.setReply_content(req.getParameter("reply_content"));
			replyDto.setReply_board(Integer.parseInt(req.getParameter("reply_board")));
			
			int member_no = (int)req.getSession().getAttribute("check");
			MemberDao memberDao = new MemberDao();
			MemberDto memberDto = memberDao.find(member_no);
			
			replyDto.setReply_nick(memberDto.getMember_nick());
			
			ReplyDao replyDao = new ReplyDao();
			replyDao.insert(replyDto);
			
			resp.sendRedirect("detail.jsp?board_no="+replyDto.getReply_board());
		}
		
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
