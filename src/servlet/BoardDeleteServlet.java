package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBoardDao;

@WebServlet(urlPatterns = "/admin/boardDelete.do")
public class BoardDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			
			AdminBoardDao adminBoardDao = new AdminBoardDao();
			boolean result = adminBoardDao.boardDelete(board_no);
			
			if(result) {
				resp.sendRedirect("boardList.jsp");
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
