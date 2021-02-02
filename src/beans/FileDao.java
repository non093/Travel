package beans;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.JdbcUtil;

 
public class FileDao {
	public static final String USERNAME = "web";
	public static final String PASSWORD = "web";
	
	//등록 기능
	public void insert(FileDto fileDto) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "insert into tmp_file values(file_seq.nextval, ?, ?, ?, ?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, fileDto.getUpload_name());
		ps.setString(2, fileDto.getSave_name());
		ps.setLong(3, fileDto.getFile_size());
		ps.setString(4, fileDto.getFile_type());
		ps.setInt(5, fileDto.getMember_no());
		ps.execute();
		
		con.close();
		
	}

//	단일 조회 기능
	public FileDto find(int member_no) throws Exception {
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "select * from tmp_file where member_no=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		ResultSet rs = ps.executeQuery(); 
		
		FileDto fileDto;
		if(rs.next()) {
			fileDto = new FileDto();
			fileDto.setFile_no(rs.getInt("file_no"));
			fileDto.setUpload_name(rs.getString("upload_name"));
			fileDto.setSave_name(rs.getString("save_name"));
			fileDto.setFile_size(rs.getLong("file_size"));
			fileDto.setFile_type(rs.getString("file_type"));
		}
		else {
			fileDto = null;
		}
		con.close();
		return fileDto;
	}
	
	//회원 탈퇴 시, 파일디비에 있는 정보 삭제
	public void delete(int member_no)throws Exception{
		Connection con = JdbcUtil.getConnection(USERNAME, PASSWORD);
		
		String sql = "delete tmp_file where member_no =?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, member_no);
		ps.execute();
		con.close();
		
		

 	}
	
//	회원 탈퇴 시, 저장된 실제 이미지 파일 삭제
	public void delete_img(int member_no)throws Exception{
		FileDao fileDao = new FileDao();
		FileDto fileDto = fileDao.find(member_no); 
		String fileName = fileDto.getSave_name(); //지울 파일명
		String fileDir = "/Users/ah-hyeon/Documents/file"; //지울 파일이 존재하는 디렉토리
		String filePath = fileDir + "/"; //파일이 존재하는 실제경로
		filePath += fileName;

		File f = new File(filePath); // 파일 객체생성
		if( f.exists()) f.delete(); // 파일이 존재하면 파일을 삭제한다.



 
	}

	
}
