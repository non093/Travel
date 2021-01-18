<%@page import="travel.beans.FreeBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="travel.beans.FreeBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	//게시글 목록 출력
	FreeBoardDao freeBoardDao = new FreeBoardDao();
	
%>

<%
	//검색
	String key = request.getParameter("key");
	String value = request.getParameter("value");
	boolean isSearch = key != null && value != null;
	
	List<FreeBoardDto> freeList;
	if(isSearch){
		freeList = freeBoardDao.select(key, value); 
	}
	else{
		freeList = freeBoardDao.select();
	}
	
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/common.css">
<style>
	.table{
		color: rgb(100, 100, 100);
	}
	.table>thead>tr>th{
		border-top:2px solid black;
		border-color: rgb(150, 150, 150);
		padding: 0 0.5rem 0.5rem;
		font-size: 0.8rem;
	}
	.table>tbody>tr>td{
		border-bottom:1px solid black;
		border-color: rgb(229, 229, 229);
		font-size: 0.9rem;
	}
	.table>tbody>tr:hover{
		background-color: #f5f5f5;
	}
	.table>tbody>tr>td>a{
		color: rgb(100, 100, 100);
		text-decoration:none;
	}
	.table>tbody>tr>td>a:hover{
		text-decoration: underline;
	}
	.head-color{
		color: rgb(150, 150, 150);
	}
	.table>thead>tr>th {
		
	}
	
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
	$(function(){
		$(".write-btn").click(function(){
			location.href="<%=request.getContextPath()%>/board/free/write.jsp";
		});
		
	});
</script>
</head>
<body>

<div class="outbox" style="width:1000px">
	<div class="row"><h2>자유게시판</h2></div>
	
	<!-- 게시글 필터 -->
	<div class="row right">
		<a href="">공지숨기기</a>
		
		<select class="input input-inline" name="board_head">
		<option>사담</option>
		<option>질문</option>
		</select>
		
		<a href="">페이징개수</a>
	</div>
	
	<!-- 게시글 목록 -->
	<div class="row">
		<table class="table">
		<thead>
			<tr>
			<th width="50%"></th>
			<th>닉네임</th>
			<th>작성일</th>
			<th>조회</th>
			<th>추천</th>
			</tr>
		</thead>
		<tbody>
		
			<!-- 게시글 목록 출력 -->
			<%for(FreeBoardDto freeDto : freeList){ %>
			<tr>
				<td width="50%" class="left">

				<span class="head-color"><%=freeDto.getBoard_head() %></span>
				<a href="detail.jsp?board_no=<%=freeDto.getBoard_no() %>"><%=freeDto.getBoard_title() %></a>
				
				</td>
				<td><%=freeDto.getBoard_nick() %></td>
				<td><%=freeDto.getBoard_date() %></td>
				<td><%=freeDto.getBoard_view() %></td>
				<td><%=freeDto.getBoard_like() %></td>
			</tr>
			<%} %>
			
		</tbody>
		</table>
	</div>
	
	<!-- 글쓰기 버튼 -->
	<div class="row right"><button class="input input-inline write-btn">글쓰기</button></div>
	
	<!-- 페이지 네비게이터 -->
	<div class="row center">
		<ul class="pagination">
				<li><a href="">&lt;</a></li>
			
					<li class="active">
						<a href="">1</a>
					</li>
			
				<li><a href="">&gt;</a></li>
		</ul>
	</div>
	
	<!-- 검색창 -->
	<div class="row center">
	<form action="list.jsp" method="get">
		<select class="input input-inline" name="key">
		<option>전체</option>
		<option value="board_nick" <%if(isSearch && key.equals("board_nick")){ %>selected<%} %>>작성자</option>
		<option value="board_title" <%if(isSearch && key.equals("board_title")){ %>selected<%} %>>제목</option>
		</select>
		<%if(isSearch) {%>
		<input type="text" class="input input-inline" name="value" value="<%=value%>" required>
		<%}else{%>
		<input type="text" class="input input-inline" name="value" required>
		<%} %>
		<input type="submit" class="input input-inline" value="검색">
	</form>
	</div>
	
</div>

</body>
</html>


















