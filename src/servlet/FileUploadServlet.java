package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import beans.FileDao;
import beans.FileDto;

@WebServlet(urlPatterns = "/member/upload.do")
public class FileUploadServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
		int member_no = (int)req.getSession().getAttribute("check");
		
		String path = "/Users/ah-hyeon/Documents/file";
		int max = 10 * 1024 * 1024; //10mMB
		String encoding = "UTF-8";
		DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();//중복된거 해결
		//수신 폴더 생성
		File dir = new File(path);
		dir.mkdirs();
		
		MultipartRequest mRequest = new MultipartRequest(req, path, max, encoding,policy );//요청객체, 저장경로, 저장크기, 인코딩방식, 작명정책
			 
		FileDto fileDto = new FileDto();
		fileDto.setSave_name(mRequest.getFilesystemName("f"));
		fileDto.setUpload_name(mRequest.getOriginalFileName("f"));
		File target = mRequest.getFile("f"); 
		fileDto.setFile_size(target.length()); 
		fileDto.setFile_type(mRequest.getContentType("f"));
		fileDto.setMember_no((int)req.getSession().getAttribute("check"));
		
		FileDao fileDao = new FileDao();
		FileDto dto = fileDao.find(member_no);
		if(dto!=null) {//파일 테이블에 프로필이미지를 올린적이 있으면
			fileDao.delete_img(member_no);//실제 저장된 이미지를 삭제
			fileDao.delete(member_no);//파일디비 삭제
		}
		fileDao.insert(fileDto);//이미지 업로드
		
		resp.sendRedirect("upload.jsp");
		}
		
		catch(Exception e) {
			e.printStackTrace();
			resp.sendError(500);
		}
		
	}
//		public static void main(String[] args) {
//		String path = System.getProperty("user.dir") + "/profile_img";
//		System.out.println("path = " + path);
//		System.out.println("아아");
//	}
}
 