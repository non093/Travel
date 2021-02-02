package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.FileDao;
import beans.FileDto;
import beans.MemberDao;
import beans.MemberDto;

@WebServlet(urlPatterns = "/member/delete.do")
public class MemberDeleteServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			req.setCharacterEncoding("UTF-8");
			
			int member_no = (int)req.getSession().getAttribute("check");
			
			FileDao fileDao = new FileDao();
			
			FileDto fileDto = fileDao.find(member_no);
			if(fileDto!=null) {//파일 테이블에 프로필이미지를 올린적이 있으면
				fileDao.delete_img(member_no);//실제 저장된 이미지를 삭제
				fileDao.delete(member_no);//파일디비 삭제
			}
			
			MemberDao memberDao = new MemberDao();	
			memberDao.delete(member_no);//멤버디비 삭제
			req.getSession().removeAttribute("check");//세션 삭제


			resp.sendRedirect("../index.jsp");//홈으로 보내기
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
	}
	
}
