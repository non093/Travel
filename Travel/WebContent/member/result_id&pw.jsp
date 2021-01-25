<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>
    <%
    	String id = (String)request.getAttribute("id");
   		String pw = (String)request.getAttribute("pw");
    %>
 	
 	<%if(id!=null&&pw==null){ %>
	<h1>아이디 찾기</h1>
		<div>
			<label>입력하신 정보와 일치하는 아이디 : </label><%=id %><br>
			<a href="login.jsp">로그인</a><br>
			<a href="find_pw.jsp">비밀번호 찾기</a>
		</div>
		<%} else{ %>
	<h1>비밀번호 찾기</h1>
		<div>
			<label>입력하신 정보와 일치하는 비밀번호 : </label><%=pw %><br>
			<a href="login.jsp">로그인</a><br>
			<a href="find_id.jsp">아이디 찾기</a>
		</div>
		<%} %>
<jsp:include page="/template/footer.jsp"></jsp:include>