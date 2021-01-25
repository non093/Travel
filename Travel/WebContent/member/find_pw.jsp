<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>
    
 <style>
    	.find_pw{
    		width : 400px;
    	}
    	input{
    		width : 400px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
<form action="find_pw.do" method="post">
	<div class="find_pw">
	<h1>비밀번호 찾기</h1>
		<div>
			<a href="find_id.jsp">아이디 찾기</a>
		</div>
		<input type="text" name="member_id" placeholder="아이디" required>
		<input type="password" name="member_email" placeholder="이메일" required>
		<%if(request.getParameter("error")!=null){ %>
		<label style="color:red">회원정보가 존재하지않습니다</label>
		<%} %>
		
		<input type="submit" value="비밀번호 찾기">
	</div>
</form>
<jsp:include page="/template/footer.jsp"></jsp:include>