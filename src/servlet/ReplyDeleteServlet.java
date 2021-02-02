package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.ReplyDao;

@WebServlet(urlPatterns = "/board/reply_delete.do")
public class ReplyDeleteServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		int reply_no = Integer.parseInt(req.getParameter("reply_no"));
		int board_no = Integer.parseInt(req.getParameter("board_no"));
		
		ReplyDao replyDao = new ReplyDao();
		replyDao.delete(reply_no);
		
		resp.sendRedirect("detail.jsp?board_no="+board_no);
		}
		catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
