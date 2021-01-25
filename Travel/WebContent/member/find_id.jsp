<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>
    
 <style>
    	.find_id{
    		width : 400px;
    	}
    	input{
    		width : 400px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
<form action="find_id.do" method="post">
	<div class="find_id">
	<h1>아이디 찾기</h1>
		<div>
			<a href="find_pw.jsp">비밀 번호 찾기</a>
		</div>
		<input type="text" name="member_email" placeholder="이메일" required>
		<input type="password" name="member_pw" placeholder="비밀번호" required>
		<%if(request.getParameter("error")!=null){ %>
		<label style="color:red">회원정보가 존재하지않습니다</label>
		<%} %>
		
		<input type="submit" value="아이디 찾기">
	</div>
</form>
<jsp:include page="/template/footer.jsp"></jsp:include>