<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//사용자가 로그인 상태인지 계산하는 코드
	//로그인 상태 : session 에 check 라는 이름의 값이 존재할 경우(null이 아닌 경우)
	//로그아웃 상태 : session 에 check 라는 이름의 값이 존재하지 않을 경우(null인 경우)
	boolean isLogin = session.getAttribute("check") != null;
	
	//사용자가 관리자인지 계산하는 코드
	String auth = (String)session.getAttribute("auth");
	boolean isAdmin = isLogin && "관리자".equals(auth);
	
	
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 길</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/adminCss/adminCommon.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">

<style>
	main, header, nav, section, 
	article, footer, div,
	label, span, p {
		border: 0px dotted #ccc;
		
	}
	main{
		width:1024px;
		margin:auto;
	}		
		
    
	aside {
			margin-top:2rem;
			border-right:3px solid #F5F5F5;
            float:left;
            width:15%;
            min-height:300px;
            
    }
    section {
            float:left;
            width:85%;
            min-height:500px;
            margin-bottom:8rem;
    }
    footer {
    	font-size:10px;
    }
    .footer-info>h5{
    	text-align:left;
    	
    }
	.div-box {
		min-height: 200px;
		padding:1rem;
	} 
	header, footer, nav, section {
		padding:1rem;
	}
	a{
		text-decoration:none;
		font-size:13px;	
		
	}
	a:link {
		color:#1F1F1F;
	}
	a:visited {
		color:#1F1F1F;
	}
	a:active {
		color: #FFCD00;
	}
	a:hover {
		text-decoration: underline;
	}
	
	.outbox{
		min-height:550px;
		margin-bottom:6rem;
	
	}
	button{
		background-color:#FFFFFF;
		font-size:12px;
		
	}
	input[type=submit]{
		padding:0.5rem 1rem 0.5rem 1rem;
		font-weight:600;
		color:#323232;
		background-color:#FFCD00;
		border:none;
		font-size:12px;
		
	}
	.table > thead > tr > th{
		border-top:1px solid black;
	}
	.search{
		margin-top:3rem;
		
	}
	input[type=text], input[type=select], input[type=password], .textarea, .select{
		border:1px solid #F5F5F5;
		margin:0.5rem;
		font-size:0.5rem;
	}
	.select>option{
		font-size:10px;
	}
	.side-style{
		list-style:none;
		margin-top:4rem;
	
		
	}
	.side-style>li{
		margin:0.5rem;
		padding:0.5rem;
		
		
	}
	.table.table-horizontal > tbody > tr > th,
	.table.table-horizontal > tbody > tr > td{
    	border-bottom:1px solid #F5F5F5;
	}
	.table.table-horizontal > tbody > tr:last-child > th,
	.table.table-horizontal > tbody > tr:last-child > td{
	    border-bottom: none;
	}
	.table.table-horizontal{/*테이블 상하 테두리*/
	    border-top: 1px solid #F5F5F5;
	    border-bottom: 1px solid #F5F5F5;
	}
	.table.table-horizontal > tfoot > tr:first-child > th,
	.table.table-horizontal > tfoot > tr:first-child > td{
	    border-top: 1px solid #F5F5F5;/*하단 첫줄 테두리*/
	}
	.table.table-horizontal > thead > tr:last-child > th,
	.table.table-horizontal > thead > tr:last-child > td{
	    border-bottom: 1px solid #F5F5F5;/*상단 마지막줄 테두리*/
	}
	label, checkbox, radio{
		font-size:12px;
	}
	input::placeholder{
		font-size:10px;
	}
	input[type=radio]{
		width:10px;
		margin:0.5rem;
	}
	#menuArea {
  		height: 460px;
  		width: 150px;
	}
   	.menuBtn p {
   		z-index: 1;
   		display: block;
   		font-size:0.8em;
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
	<main>
	
	<header>
	
	<div class="div-box" >
		<a href="<%=request.getContextPath()%>">
			<img alt="로고 이미지" src="<%=request.getContextPath()%>/adminImage/logo.png" style="width:150px; height:150px;" align="left">
		</a>
		
		<nav align="right">
			<img src="<%=request.getContextPath()%>/adminImage/search-icon.png" style="width:13px;">
			<input type="text" class="input input-inline" name="key" required placeholder="검색하세요">
			<input type="submit" class="input input-inline" value="검색">
			<% if(isAdmin){%> 
			<!-- 관리자 메뉴 -->
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/admin/adminPage.jsp">관리메뉴</a>
			<% } else if(!isLogin) {%>

			<!-- 비회원 메뉴 -->
			<a href="<%=request.getContextPath()%>/member/memberLogin.jsp">로그인</a>
			<a href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a>
			<% } else{%> 

			<!-- 회원 메뉴-->
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/member/my.jsp">마이페이지</a>

			<%}%>

		</nav>
	</div>
	
	</header>
	
	
	