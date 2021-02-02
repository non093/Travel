<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	String auth = (String)session.getAttribute("auth");
	
	if(auth == null || !auth.equals("관리자")){
		response.sendError(403);
		return;
	}

%>

<jsp:include page="/template/header.jsp"></jsp:include>

<style>
	
	.row{
		padding:0.5rem;
	}
	.line{
		margin-left:17rem;
		width:60px;
		border:1px solid black;
	}

</style>

<div class="outbox center" style="width:600px">
	<div class="row">
		<h2>관리자 메뉴</h2>
	</div>
	
	<div class="line">
		
	</div>
	
	<div class="row">
		<a href="adminMemberList.jsp">회원 검색</a>
	</div>
	<div class="row">
		<a href="boardList.jsp">게시물 검색</a>
	</div>
	<div class="row">
		<a href="reportList.jsp">문제 신고</a>
	</div>
	
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>