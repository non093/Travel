package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BoardDao;
import beans.BoardDto;

@WebServlet(urlPatterns = "/board/edit.do")
public class TBoardEditServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			req.setCharacterEncoding("UTF-8");
			BoardDto dto = new BoardDto();
			
			dto.setBoard_no(Integer.parseInt(req.getParameter("board_no")));
			dto.setBoard_cate(req.getParameter("board_cate"));
			dto.setBoard_head(req.getParameter("board_head"));
			dto.setBoard_title(req.getParameter("board_title"));
			dto.setBoard_content(req.getParameter("board_content"));
			dto.setBoard_address(req.getParameter("board_address"));
			
			BoardDao dao = new BoardDao();
			boolean result = dao.update(dto);
			
			if(result) {
				resp.sendRedirect("detail.jsp?board_no="+dto.getBoard_no());
			}
			else {
				resp.sendRedirect("edit_fail.jsp");
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}

}
