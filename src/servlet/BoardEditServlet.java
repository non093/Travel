package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AdminBoardDao;
import beans.BoardDto;

@WebServlet(urlPatterns = "/admin/boardEdit.do")
public class BoardEditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			준비 : 데이터 4개(번호,말머리,제목,내용)
			req.setCharacterEncoding("UTF-8");
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_no(Integer.parseInt(req.getParameter("board_no")));
			boardDto.setBoard_cate(req.getParameter("board_cate"));
			boardDto.setBoard_head(req.getParameter("board_head"));
			boardDto.setBoard_title(req.getParameter("board_title"));
			boardDto.setBoard_open(Integer.parseInt(req.getParameter("board_open")));
			
//			처리 : 수정
			AdminBoardDao boardDao = new AdminBoardDao();
			boardDao.update(boardDto);
			
//			출력 : 상세페이지로 이동
//			resp.sendRedirect("detail.jsp?board_no="+boardDto.getBoard_no());
			resp.sendRedirect(req.getContextPath()+"/admin/boardDetail.jsp?board_no="+boardDto.getBoard_no());
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}