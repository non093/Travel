<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>

<style>
		.mem_input{
      		margin : 0 !important;
      		padding-left: 1rem;

      	}
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

<form action="adminLogin.do" method="post">
<div class="outbox" style="width:400px">
	<div class="row center">
		<h2>로그인</h2>
	</div>
	<div class="row center login">
		<span class="login-type"><a href="../member/memberLogin.jsp">회원 로그인</a></span>
		<span class="login-type"><a href="../admin/adminLogin.jsp">관리자 로그인</a></span>
	</div>
	<div class="mem_row">
		<input type="text" name="admin_id" class="mem_input" placeholder="ID" required>
	</div>
	<div class="mem_row">
		<input type=password name="admin_pw" class="mem_input"  placeholder="PASSWORD" required>
	</div>
	<%if(request.getParameter("error")!=null){ %>
	<div class="row" style="color:red;">
		입력하신 정보가 맞지 않습니다
	</div>
	<%}%>
	<div>
		<input type="submit" value="로그인" class="mem_input submit">
	</div>
</div>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>
