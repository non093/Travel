<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>

<form action="adminLogin.do" method="post">
<div class="outbox" style="width:400px">
	<div class="row center">
		<h2>로그인</h2>
	</div>
	<div class="row">
		<label>ID</label>
		<input type="text" name="admin_id" class="input" required>
	</div>
	<div class="row">
		<label>Password</label>
		<input type=password name="admin_pw" class="input" required>
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
