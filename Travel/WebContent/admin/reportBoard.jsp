<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="travel.beans.*" %>

<%
	int board_no = Integer.parseInt(request.getParameter("board_no"));
	
	//2.조회수를 증가시킨 후 게시글 정보를 구한다
	AdminBoardDao adminBoardDao = new AdminBoardDao();
	BoardDto boardDto = adminBoardDao.find(board_no);
	
	//참고 : 작성자의 다른 정보가 필요할 경우 검색한다.
	MemberDao memberDao = new MemberDao();
	MemberDto writerDto = memberDao.find(boardDto.getBoard_nick());


%>


<jsp:include page="/template/header.jsp"></jsp:include>

<script>
	$(function(){
		$(".list-btn").click(function(){
			//location.href = "list.jsp";//상대경로
			location.href = "boardDetail.jsp?board_no=<%=board_no%>";
		});
	});
</script>

<div class="outbox" style="width:400px">
	<button class="input input-inline list-btn" style="margin-left:355px;">목록</button>
	
	<div class="row center">
		<h2>신고</h2>
		<h5>이 게시물을 신고하는 이유</h5>
	</div>
	<div class="row center" style="border-top:1px solid #F5F5F5;border-bottom:1px solid #F5F5F5; ">
		<h5><%=board_no%>번 게시글</h5>
		<span><%=boardDto.getBoard_content()%></span>
	</div>
	
	<form action="reportBoard.do" method="post">
	
	<div>
		<input type="radio" name="report_header"  value="게시글" checked>
		<label>게시글</label>
	</div>
	
		
	
	<div class="row">
		<input type="text" class="input" name="report_title" value="<%=board_no%>번 게시글" required>
	</div>
	
	<div>
		<input type="radio" name="report_content" value="부적절한 게시물" checked >
		<label>부적절한 게시물</label>
		
		<input type="radio" name="report_content" value="스팸">
		<label>스팸</label>
	</div>	
	
	<div class="row">
		<input type="submit" class="input" value="보내기">
	</div>
	 
	</form>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>