package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import beans.MemberDao;
import beans.MemberDto;
import beans.ReportDao;
import beans.ReportDto;

/**
 * 게시글 작성 서블릿
 * = 등록한 글의 번호를 무조건 알아내야 한다
 */
@WebServlet(urlPatterns = "/admin/reportBoard.do")
public class ReportBoardServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			준비 : board_header, board_title, board_content, board_writer
//			= 3개는 파라미터(board_header, board_title, board_content)에서 가져올 수 있다.
//			= 1개는 세션의 정보를 이용하여 구해야 한다(member_no -> board_writer)
			req.setCharacterEncoding("UTF-8");
			ReportDto reportDto = new ReportDto();
			
			reportDto.setReport_header(req.getParameter("report_header"));
			reportDto.setReport_title(req.getParameter("report_title"));
			reportDto.setReport_content(req.getParameter("report_content"));
			
//			현재 로그인한 사용자 정보를 불러오는 코드
			int member_no = (int)req.getSession().getAttribute("check");
			MemberDao memberDao = new MemberDao();
			MemberDto memberDto = memberDao.find(member_no);
			
//			memberDto의 member_id를 boardDto의 board_writer에 설정
			reportDto.setReport_nick(memberDto.getMember_nick());
			
//			처리 : BoardDao를 사용
//			1. 시퀀스 번호 생성 - .getSequence()
//			2. 등록 - .writeWithPrimaryKey()
			ReportDao reportDao = new ReportDao();
			int report_no = reportDao.getSequence();	//시퀀스번호생성
			reportDto.setReport_no(report_no);			//생성된 번호를 DTO에 설정
			reportDao.boardWithPrimaryKey(reportDto);	//설정된 정보를 등록!
			
			
//			출력
			//resp.sendRedirect("reportDetail.jsp?report_no="+report_no);
			//resp.sendRedirect(req.getHeader("referer"));
			resp.sendRedirect("reportList.jsp");
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}
