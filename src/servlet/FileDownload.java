package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.FileDao;
import beans.FileDto;

@WebServlet(urlPatterns = "/member/download.do")
public class FileDownload extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		int member_no = (int)req.getSession().getAttribute("check");
		
		FileDao fileDao = new FileDao();
		FileDto fileDto = fileDao.find(member_no);
		
		String path = "/Users/ah-hyeon/Documents/file";
		File target = new File(path, fileDto.getSave_name());
		byte [] data = new byte[(int)target.length()]; 
		FileInputStream in = new FileInputStream(target); 
		in.read(data); 
		
		//출력
		
		resp.setHeader("Content-Type", "application/octet-stream");//파일 유형
		resp.setHeader("Content-Encoding", "UTF-8");//파일인코딩
		resp.setHeader("Content-Length", String.valueOf(fileDto.getFile_size()));//파일크기
 		resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(fileDto.getUpload_name(), "UTF-8")+"\"");
																				//띄어쓰기, 한글 처리

		resp.getOutputStream().write(data);//읽어온 데이터를 사용자에게 전송
		}
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
}
