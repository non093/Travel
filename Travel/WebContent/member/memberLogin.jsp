<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>

<style>
	.login{
		font-size:10px;
		
	}
	.login-type>a{
		color:#AEB3B8;
	}
	
	.login-type:last-child{
		margin-left:100px;
		
	}
</style>

<form action="memberLogin.do" method="post">
<div class="outbox" style="width:400px">
	<div class="row center">
		<h2>로그인</h2>
	</div>
	<div class="row center login">
		<span class="login-type"><a href="../member/memberLogin.jsp">회원 로그인</a></span>
		<span class="login-type"><a href="../admin/adminLogin.jsp">관리자 로그인</a></span>
	</div>
	<div class="row">
		
		<input type="text" name="member_id" class="input" placeholder="ID" required>
	</div>
	<div class="row">
		
		<input type=password name="member_pw" class="input" placeholder="PASSWORD" required>
	</div>
	<%if(request.getParameter("error")!=null){ %>
	<div class="row" style="color:red;">
		입력하신 정보가 맞지 않습니다
	</div>
	<%}%>
	<div>
		<input type="submit" value="로그인" class="input">
	</div>
</div>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>
