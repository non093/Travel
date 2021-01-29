package travel.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import travel.beans.ReportDao;
import travel.beans.ReportDto;

@WebServlet(urlPatterns = "/admin/reportEdit.do")
public class ReportEditServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
//			준비 : 데이터 4개(번호,말머리,제목,내용)
			req.setCharacterEncoding("UTF-8");
			ReportDto reportDto = new ReportDto();
			reportDto.setReport_no(Integer.parseInt(req.getParameter("report_no")));
			reportDto.setReport_header(req.getParameter("report_header"));
			reportDto.setReport_title(req.getParameter("report_title"));
			reportDto.setReport_content(req.getParameter("report_content"));
			reportDto.setReport_answer(req.getParameter("report_answer"));
			reportDto.setReport_qa(Integer.parseInt(req.getParameter("report_qa")));
			
			
//			처리 : 수정
			ReportDao reportDao = new ReportDao();
			reportDao.update(reportDto);
			
//			출력 : 상세페이지로 이동
//			resp.sendRedirect("detail.jsp?board_no="+boardDto.getBoard_no());
			resp.sendRedirect(req.getContextPath()+"/admin/reportDetail.jsp?report_no="+reportDto.getReport_no());
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
}