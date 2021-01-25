 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	boolean isLogin= session.getAttribute("check")!=null;
    	boolean isAdmin= session.getAttribute("auth")!=null;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	main {
		width:1024px;
		margin:auto;
	}
	
 	header, footer, nav, section {
		padding:1rem;
	}
	
 	section {
		min-height: 450px;
		
	}
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>

</head>
<body>
	<main>
		<header>semi</header>
		<nav>
			<%if(!isLogin) {%>
			<a href="<%=request.getContextPath()%>">홈</a>
			<a href="<%=request.getContextPath()%>/member/login.jsp">로그인</a>
			<a href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a>
		
			<%}else {%> 
			<a href="<%=request.getContextPath()%>">홈</a>
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/member/my.jsp">회원 정보</a>
			<a href="<%=request.getContextPath()%>/member/edit.jsp">회원 정보 수정</a>
			<%} %>
			
		</nav>
		<section>

	 