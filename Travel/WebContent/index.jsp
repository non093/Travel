<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/template/header.jsp"></jsp:include>
	<article>
		<aside>
			<li><a href="<%=request.getContextPath()%>/member/logout.do">전체 게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/member/logout.do">공지 게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/member/logout.do">자유 게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/member/logout.do">여행 게시판</a></li>
		</aside>
		<section>
			<div  align="right">
				<img alt="로고 이미지" src="image/logo.png" style="width:200px; height:200px;">
				<img alt="로고 이미지" src="image/logo.png" style="width:200px; height:200px;">
				<img alt="로고 이미지" src="image/logo.png" style="width:200px; height:200px;">
			</div>
			<div  align="right">
				<img alt="로고 이미지" src="image/logo.png" style="width:200px; height:200px;">
				<img alt="로고 이미지" src="image/logo.png" style="width:200px; height:200px;">
				<img alt="로고 이미지" src="image/logo.png" style="width:200px; height:200px;">
			</div>
<jsp:include page="/template/footer.jsp"></jsp:include>