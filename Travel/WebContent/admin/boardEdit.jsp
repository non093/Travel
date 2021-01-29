<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="travel.beans.*" %>

<%
// 	상세보기처럼 번호를 이용해서 글을 불러온다(조회수 증가는 하지 않음)
	int board_no = Integer.parseInt(request.getParameter("board_no"));
	AdminBoardDao boardDao = new AdminBoardDao();
	BoardDto boardDto = boardDao.find(board_no);
%>

<%
	String auth = (String)session.getAttribute("auth");
	boolean isAdmin = auth.equals("관리자");

%>


<jsp:include page="/template/header.jsp"></jsp:include>

<script>
	//유형에 따른 select 선택
	$(function(){
		$("select[name=board_head]").val("<%=boardDto.getBoard_head_string()%>"); 
		$("select[name=board_cate]").val("<%=boardDto.getBoard_cate_string()%>"); 
		
		
		$(".list-btn").click(function(){
			//location.href = "list.jsp";//상대경로
			history.back()
		});
	
	});
	

</script>

<div class="outbox" style="width:600px">
	<button class="input input-inline list-btn" style="margin-left:555px;">목록</button>
	
	<div class="row center">
	
		<h2>게시글 수정</h2>
		</div>
	<div class="row center">상대방에 대한 인신공격은 예고 없이 삭제될 수 있습니다</div>
	
	<form action="<%=request.getContextPath()%>/admin/boardEdit.do" method="post">
	
	<!-- 사용자 몰래 번호를 첨부 -->
	<input type="hidden" name="board_no" value="<%=boardDto.getBoard_no()%>">
	
	<div class="row">
		<label>카테고리</label>
		<select name="board_cate" class="input select">
			<option value="">말머리를 선택하세요</option>
			<option value="공지">공지</option>
			<option value="자유">자유</option>
			<option value="여행">여행</option>
		
		</select>	
	</div>
	
	
	<div class="row">
		<label>말머리</label>
		<select name="board_head" class="input select">
			<option value="">말머리를 선택하세요</option>
			<option value="강원도">강원도</option>
			<option value="서울">서울</option>
			<option value="인천">인천</option>
			<option value="경기도">경기도</option>
			<option value="충남">충남</option>
			<option value="충북">충북</option>
			<option value="경남">경남</option>
			<option value="대구">대구</option>
			<option value="대전">대전</option>
			<option value="세종">세종</option>
			<option value="울산">울산</option>
			<option value="부산">부산</option>
			<option value="전북">전북</option>
			<option value="전남">전남</option>
			<option value="광주">광주</option>
			<option value="사담">사담</option>
			<option value="질문">질문</option>
		</select>	
	</div>
	
	<div class="row">
		<label>제목</label>
		<input type="text" class="input" name="board_title" value="<%=boardDto.getBoard_title()%>" required>
	</div>
		
		<div class="row">
				<label>상태</label>
	
	<%if(isAdmin){ %>
		
			<%if(boardDto.getBoard_open() > 0){ %>
			<div>
				<input type="radio" name="board_open"  value=0>
				<label>공개</label>
			</div>	
			<%}else{%>		
			<div>
				<input type="radio" name="board_open"  value=1>
				<label>비공개</label>
			</div>	
			<%}%>
	<%}else{ %>
			<div>
				<input type="radio" name="board_open" checked value=0>
				<label>공개</label>
			</div>	
	<%}%>
	</div>	
	
	<div class="row">
		<input type="submit" class="input" value="수정">
	</div>
	 
	</form>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>