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

<div class="outbox center" style="width:600px">
	<div class="row">
		<h2>관리자 메뉴</h2>
	</div>
	<div class="row">
		<a href="list.jsp">회원 검색</a>
	</div>
	<div class="row">
		<a href="rank.jsp">포인트 현황</a>
	</div>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>