<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="beans.*" %>
    <%@ page import="util.*" %>
    <%
	boolean isLogin = session.getAttribute("check") != null;
	
	String auth = (String)session.getAttribute("auth");
	boolean isAdmin = isLogin && auth.equals("관리자");
	
	VisitCountDAO visitCountDAO = new VisitCountDAO();
	
	int todayCount = 0;
    int totalCount = 0;
    
	    String visit = session.getId();
		
	    boolean isOverlap = visitCountDAO.visitCheck(visit);
	    
	    if(isOverlap){
			visitCountDAO.setVisitTotalCount(visit);
	    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>오늘의 길</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
    <link rel="stylesheet" type="text/css" href="/Travel/css/main.css">
<style>
</style>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>
    $(document).ready(function(){
    	$("#menuArea").show();
    $(".menuBtn").click(function(){
       $(".menuBtn p").toggle();
    $('#menuArea').animate({width:'toggle'});
    	})
	})
</script>
</head>
<body>
<main>
	<article>
		<aside>
			<a href="<%=request.getContextPath()%>/index.jsp">
				<img alt="로고 이미지" src="<%=request.getContextPath()%>/image/logo.png" style="width:270px; height:270px;" align="left">
			</a>
			<div class="menuBtn">
        		<p>&equiv;닫힘</p>
        		<p style="display:none;">&equiv;열림</p>
    		</div>
    		<div id="menuArea">
    			<br>
    			<br>
    			<br>
    			<br>
    			<br>
    			<br>
    			<br>
    			<ul>
					<li><a href="<%=request.getContextPath()%>/board/board.jsp">전체 게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/member/Board_alert.do">공지 게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/member/Board_free.do">자유 게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/member/Board_travel.do">여행 게시판</a></li>
					<li>전체 방문자 : <%=visitCountDAO.getVisitTotalCount()%></li>
					<li>오늘 방문자 : <%=visitCountDAO.getVisitTodayCount()%></li>
				</ul>
    		</div>
    	</aside>
    <header>
		<div>
			<form align="right">
				<input type="text" class="input input-inline" name="key" required placeholder="검색하세요">
				<input type="submit" class="input input-inline" value="검색">
				<%if(!isLogin){ %>
					<a href="<%=request.getContextPath()%>/member/login.jsp"><input type="button" value="로그인"></a>
					<a href="<%=request.getContextPath()%>/member/join.jsp"><input type="button" value="회원가입"></a>
				<%}else{ %>
					<div class="dropdown">
				        <label for="ck">아이디</label>
				        <input type="checkbox" id="ck">
				        <a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
						<a href="<%=request.getContextPath()%>/member/my.jsp">내정보</a>
				    </div>
				<%} %>
				<%if(isAdmin){ %>
					<a href="<%=request.getContextPath()%>/admin/home.jsp"><input type="button" value="관리메뉴"></a>
				<%} %>
			</form>
		</div>
	</header>
		<section>