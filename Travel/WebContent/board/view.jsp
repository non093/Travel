<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="beans.*" %>

<%
	int board_no = Integer.parseInt(request.getParameter("board_no"));
	
	BoardDao dao = new BoardDao();
	BoardDto dto = dao.find(board_no);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<style>
div {
	padding: 0.2rem;
}

.outbox {
	width: 800px;
	height: 600px;
}

.content {
	height: 400px;
}

.title {
	width: 500px;
	margin: 10px 0px;
	padding: 10px;
	height: 20px;
}

.writer {
	width: 150px;
}

.content-info, .content-date {
	width: 400px;
}

.inbox {
	border: 0.1px solid black;
	width: 500px;
	background-color: #F6F6F6;
}
</style>
</head>
<body>
	<div class="outbox">
		<div class="inbox">
			<div class="title">[<%=dto.getBoard_head()%>] <%=dto.getBoard_title() %></div>
			<div class="writer">작성자 ▶ <%=dto.getBoard_nick() %></div>
			<div class="content-info">
				번호 ▶ <span><%=dto.getBoard_no() %></span>
				 조회 ▶ <span><%=dto.getBoard_view() %></span> 
				 추천 ▶ <span><%=dto.getBoard_like() %></span>
			</div>
			<div class="content-date">
				작성시간 ▶ <span><%=dto.getBoard_date() %></span>
			</div>
		</div>
		<!-- 내용 -->
		<div class="content"><%=dto.getBoard_content() %></div>
		<!-- (추천 버튼)-->
		
		<!-- (수정 삭제) -->
		
		<!-- (이전글 다음글) -->
	</div>
</body>
</html>