<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 길</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/common.css">
<style>
	main, header, nav, section, 
	aside, article, footer, div,
	label, span, p {
		border: 1px dotted #ccc;
	}
	article::after {
            content:"";
            display: block;
            clear:both;
    }
	aside {
            float:left;
            width:15%;
            min-height:500px;
    }
    section {
            float:left;
            width:85%;
            min-height:500px;
    }
	div {
		min-height: 250px;
	} 
	header, footer, nav, section {
		padding:1rem;
	}
	
</style>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script>

</script> 
</head>
<body>
	<div >
		<img alt="로고 이미지" src="image/logo.png" style="width:250px; height:250px;" align="left">
		<nav align="right">
			<input type="text" class="input input-inline" name="key" required placeholder="검색하세요">
			<input type="submit" class="input input-inline" value="검색">
			<a href="<%=request.getContextPath()%>/member/join.jsp">회원가입</a>
			<a href="<%=request.getContextPath()%>/member/login.jsp">로그인</a>
			<a href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
			<a href="<%=request.getContextPath()%>/member/my.jsp">내정보</a>
		</nav>
	</div>