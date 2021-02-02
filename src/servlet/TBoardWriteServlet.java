package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BoardDao;
import beans.BoardDto;
import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/board/write.do")
public class TBoardWriteServlet extends HttpServlet {

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			req.setCharacterEncoding("UTF-8");
			BoardDto boardDto = new BoardDto();
			
//			boardDto.setBoard_nick(req.getParameter("board_nick"));
			boardDto.setBoard_cate(req.getParameter("board_cate"));
			boardDto.setBoard_head(req.getParameter("board_head"));
			boardDto.setBoard_title(req.getParameter("board_title"));
			boardDto.setBoard_address(req.getParameter("board_address"));
			boardDto.setBoard_content(req.getParameter("board_content"));
			
			//로그인한 사용자 정보
			int member_no = (int)req.getSession().getAttribute("check");
			MemberDao memberDao = new MemberDao();
			MemberDto memberDto = memberDao.find(member_no);
//			
			boardDto.setBoard_nick(memberDto.getMember_nick());
			
			BoardDao boardDao = new BoardDao();
			
			int board_no = boardDao.getSequence();
			boardDto.setBoard_no(board_no);
			boardDao.writeWithPrimaryKey(boardDto);
			
			resp.sendRedirect("detail.jsp?board_no="+board_no);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500);

		}
	}
}
