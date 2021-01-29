<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
 <style>
     	 
    	 
    	a{
    		float : right;
    		font-weight: 100;
    	}
    	a:hover{
    		color : black;
    		font-weight: bold;
    	}
    	.mem_input{
      		padding-left: 1rem;

      	}
        </style>
<form action="find_id.do" method="post">
	<div class="mem_outbox">
		<div class="mem_row">
			<h1>아이디 찾기
			<a href="find_pw.jsp">비밀 번호 찾기</a>
			</h1>
		</div>
		<div class="mem_row">
			<input type="text" name="member_email" class="mem_input" placeholder="이메일" required>
		</div>
		<div class="mem_row">
			<input type="password" name="member_pw" class="mem_input" placeholder="비밀번호" required>
		</div>
		
			<%if(request.getParameter("error")!=null){ %>
			<label class="error">회원정보가 존재하지않습니다</label>
			<%} %>
		
		<div class="mem_row">
			<input type="submit" class="mem_input submit" value="아이디 찾기">
		</div>
	</div>
</form>
