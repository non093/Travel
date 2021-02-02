package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BoardDao;

@WebServlet(urlPatterns = "/board/delete.do")
public class TBoardDeleteServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int board_no = Integer.parseInt(req.getParameter("board_no"));
			
			BoardDao boardDao = new BoardDao();
			boardDao.delete(board_no);
			
			resp.sendRedirect(req.getContextPath()+"/board/travellist.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
