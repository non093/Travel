package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BoardDao;
import beans.BoardDto;

@WebServlet(urlPatterns = "/board/write.do")
public class TBoardWriteServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			req.setCharacterEncoding("UTF-8");
			BoardDto dto = new BoardDto();
			dto.setBoard_cate(req.getParameter("board_cate"));
			dto.setBoard_head(req.getParameter("board_head"));
			dto.setBoard_title(req.getParameter("board_title"));
			dto.setBoard_content(req.getParameter("board_content"));
			
			BoardDao dao = new BoardDao();
			dao.write(dto);
			
			resp.sendRedirect("/Travel/board/write_result.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);

		}
	}
}
