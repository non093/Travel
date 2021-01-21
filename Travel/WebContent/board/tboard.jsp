<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="beans.*"%>
<%@ page import="java.util.*"%>

<%
	//검색 구문
	String type = request.getParameter("type");
	String keyword = request.getParameter("keyword");
	
	boolean isSearch = type != null && keyword != null;
	
	//리스트 출력
	BoardDao dao = new BoardDao();
	List<BoardDto> list;
	
	if(isSearch) {
			list = dao.select(type, keyword);
	}
	else{
			list = dao.select();
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여행게시판</title>
<style>
/* 초기화 디자인 */
html, body {
	margin: 0;
	padding: 0;
}

* {
	box-sizing: border-box;
}

.outbox {
	margin: 0 auto;
}

.table {
	border: 1px solid black;
	border-collapse: collapse;
}

.row {
	margin: 0.5rem 0;
}

.table>trline {
	border-bottom: 1px solid black;
}

th {
	padding: 0 10px;
}

td {
	border: 1px solid black;
}

.right {
	float: right;
}

.center {
	margin: 40px auto;
	width: 40%;
}
</style>
</head>
<body>
	<div class="outbox" style="width: 800px;">
		<!-- 리스트 목록 출력 -->
		<div class="row">
			<table class="table">
				<thead>
					<tr class="trline">
						<th>번호</th>
						<th style="width: 60%;">제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회</th>
						<th>추천</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (BoardDto dto : list) {
					%>
					<tr>
						<td><%=dto.getBoard_no()%></td>
						<td style="width: 60%;">
						<a href="view.jsp?board_no=<%=dto.getBoard_no() %>">
						[<%=dto.getBoard_head()%>]
						<%=dto.getBoard_title()%>
						</a>
						</td>
						<td><%=dto.getBoard_nick()%></td>
						<td><%=dto.getBoard_date()%></td>
						<td><%=dto.getBoard_view()%></td>
						<td><%=dto.getBoard_like()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<!-- 등록 -->
		<div>
			<button class="right">
				<a href="write.jsp">등록</a>
			</button>
		</div>
		<!-- 검색 영역 -->
		<div class="row center">
			<form action="tboard.jsp" method="get">
				<select name="type" class="input">
					<option value="board_title">제목</option>
					<option value="board_cate">대분류</option>
					<option value="board_head">소분류</option>
					<option value="board_nick">작성자</option>
					<option value="board_content">내용</option>
				</select> 
				<input type="text" name="keyword" placeholder="검색어를 입력하세요" class="input">
				<input type="submit" value="검색" class="input">

			</form>
		</div>
	</div>

</body>
</html>