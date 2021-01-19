<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	boolean isLogin = session.getAttribute("check") != null;
	
	String auth = (String)session.getAttribute("auth");
	boolean isAdmin = isLogin && auth.equals("관리자"); 
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가만든 홈페이지</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<style>
	main, header, nav, section, aside, srticle, footer, div, label, span, p {
		border: 1px dotted #ccc;
	}
	main{
		width:1024px;
		margin:auto;
	}
	header, footer, nav, section {
		padding:1rem;
	}
	section{
		min-height: 450px;
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>

</script>
</head>
<body>
	<main>
		<header>
			<h1 class="center">JSP로 홈페이지 만들기</h1>
		</header>
		<nav>
			<!-- 비회원  -->
			<%if(!isLogin){%>			
			<a href="<%=request.getContextPath()%>">홈으로</a>
			<a href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a>
			<a href="<%=request.getContextPath()%>/adminLog/admin_login.jsp">로그인</a>
			<a href="<%=request.getContextPath()%>/board/list.jsp">게시판</a>
			<%}else{ %>
			<!-- 회원 -->
			<a href="<%=request.getContextPath()%>">홈으로</a>
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/member/my.jsp">내정보</a>
			<a href="<%=request.getContextPath()%>/board/list.jsp">게시판</a>
			<%} %>
			
			<%if(isAdmin){ %>
			<a href="<%=request.getContextPath()%>/admin/admin_page.jsp">관리메뉴</a>
			<%} %>
		</nav>
		<section>