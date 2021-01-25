<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="/template/header.jsp"></jsp:include>
    

 <style>
    	.login{
    		width : 400px;
    	}
    	input{
    		width : 400px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
<form action="login.do" method="post">
	<div class="login">
	<h1>로그인</h1>
		<input type="text" name="member_id" placeholder="아이디" required>
		<input type="password" name="member_pw" placeholder="비밀번호" required>
		<%if(request.getParameter("error")!=null){ %>
		<label style="color:red">입력하신 정보가 맞지않습니다</label>
		<%} %>
		<div>
			<a href="join.jsp">회원가입</a>
			<a href="find_id.jsp">아이디 찾기</a>
			<a href="find_pw.jsp">비밀 번호 찾기</a>
		</div>
		<input type="submit" value="로그인">
	</div>
</form>
<jsp:include page="/template/footer.jsp"></jsp:include>