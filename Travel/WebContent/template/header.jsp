

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
<title>오늘의 길</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
 
 <script>

</script> 
</head>
<body>
	<div >
		<a href="<%=request.getContextPath()%>"><img alt="로고 이미지" src="<%=request.getContextPath()%>/image/logo.png" style="width:250px; height:250px;" align="left"></a>
		<nav align="right">
			<input type="text" class="input input-inline" name="key" required placeholder="검색하세요">
			<input type="submit" class="input input-inline" value="검색">
			<% if(isAdmin){%> 
			<!-- 관리자 메뉴 -->
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="#">관리메뉴</a>
			<% } else if(!isLogin) {%>
			
			<!-- 비회원 메뉴 -->
			<a href="<%=request.getContextPath()%>/member/login.jsp">로그인</a>
			<a href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a>
			<% } else{%> 
			
			<!-- 회원 메뉴-->
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/member/my.jsp">마이페이지</a>
 	
			<%}%>
		</nav>
	</div>

