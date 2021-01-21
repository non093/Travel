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
	
	visitCountDAO.setVisitTotalCount();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>오늘의 길</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<style>
	article::after {
            content:"";
            display: block;
            clear:both;
    }
	aside {
            float:left;
            width:20%;
            min-height:500px;
    }
    section {
            float:left;
            width:80%;
            min-height:500px;
    }
	div {
		min-height: 250px;
	} 
	header, footer, section {
		padding:1.5rem;
	}
    #menuArea {
  		height: 500px;
  		width: 250px;
 		background-color: grey;
	}
   	.menuBtn p {
   		position: absolute;
   		top: 245px;
   		left: 200px;
   		z-index: 1;
   		display: block;
   		font-size:1em;
   		color: #444;
   		cursor: pointer;
	}
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
	<article>
		<aside>
			<a href="<%=request.getContextPath()%>/index.jsp">
				<img alt="로고 이미지" src="image/logo.png" style="width:250px; height:250px;" align="left">
			</a>
			<div class="menuBtn">
        		<p>&equiv;닫힘</p>
        		<p style="display:none;">&equiv;열림</p>
    		</div>
    		<div id="menuArea">
    			<br>
    			<ul>
					<li><a href="<%=request.getContextPath()%>/member/Board.do">전체 게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/member/Board_alert.do">공지 게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/member/Board_free.do">자유 게시판</a></li>
					<li><a href="<%=request.getContextPath()%>/member/Board_travel.do">여행 게시판</a></li>
					<li>전체 : <%=visitCountDAO.getVisitTotalCount()%></li>
					<li>오늘 : <%=visitCountDAO.getVisitTodayCount()%></li>
				</ul>
    		</div>
    	</aside>
    <header>
		<div>
			<form align="right">
				<input type="text" class="input input-inline" name="key" required placeholder="검색하세요">
				<input type="submit" class="input input-inline" value="검색">
				<%if(!isLogin){ %>
					<a href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a>
					<a href="<%=request.getContextPath()%>/member/login.jsp">로그인</a>
				<%}else{ %>
					<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
					<a href="<%=request.getContextPath()%>/member/my.jsp">내정보</a>
				<%} %>
				<%if(isAdmin){ %>
					<a href="<%=request.getContextPath()%>/admin/home.jsp">관리메뉴</a>
				<%} %>
			</form>
		</div>
	</header>
		<section>