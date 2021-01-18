package travel.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import travel.util.JdbcUtil;

public class FreeBoardDao {
	
	//게시글 작성
		public void insert(FreeBoardDto dto) throws Exception{
			Connection con = JdbcUtil.getConnection("semi", "semi");
			
			String sql = "insert into board values(BOARD_SEQ.nextval, 'admin', ?, ?, ?, ?, sysdate, 0, 0)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dto.getBoard_cate());
			ps.setString(2, dto.getBoard_head());
			ps.setString(3, dto.getBoard_title());
			ps.setString(4, dto.getBoard_content());
			ps.execute();
			
			con.close();
		}
		
	//게시글 목록 조회
	public List<FreeBoardDto> select() throws Exception{
		Connection con = JdbcUtil.getConnection("semi", "semi");
		
		String sql ="select * from board where board_cate ='자유' order by board_no desc";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>();
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}
		
		con.close();
		
		return list;
	}
	
	//게시글 검색 목록 조회
	public List<FreeBoardDto> select(String key, String value) throws Exception{
		Connection con = JdbcUtil.getConnection("semi", "semi");
		
		String sql ="select * from board where board_cate ='자유' and instr(#1, ?) > 0 "
				+ "order by board_no desc";
		sql = sql.replace("#1", key);
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, value);
		ResultSet rs = ps.executeQuery();
		
		List<FreeBoardDto> list = new ArrayList<>();
		while(rs.next()) {
			FreeBoardDto dto = new FreeBoardDto();
			dto.setBoard_no(rs.getInt("board_no"));
			dto.setBoard_nick(rs.getString("board_nick"));
			dto.setBoard_head(rs.getString("board_head"));
			dto.setBoard_title(rs.getString("board_title"));
			dto.setBoard_date(rs.getDate("board_date"));
			dto.setBoard_like(rs.getInt("board_like"));
			dto.setBoard_view(rs.getInt("board_view"));
			list.add(dto);
		}
		
		con.close();
		
		return list;
	}
}



























