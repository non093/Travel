<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
     
     <%
    	String id = (String)request.getAttribute("id");
   		String pw = (String)request.getAttribute("pw");
    %>
     <script>
    	$(function(){
    		$("#login_btn").click(function(e){
    			e.preventDefault();
				close();//로그인 버튼 누르면 팝업닫기

     		 })
    	});
    	
    </script>
   <style>	
   		.mem_outbox{
   			margin : 4rem auto;
   			text-align: center;
   		}
   		a{
          
           margin-left : 1rem;
           
       	}
       	a:hover{
       		color : gray;
       	}
   		
       
   </style>
 	
 	<%if(id!=null&&pw==null){ %>
		<div class="mem_outbox">
			<h3>입력하신 정보와 일치하는 아이디</h3>
			<%=id %><br><br>
			<a href="login.jsp" id="login_btn">로그인</a> 
			<a href="find_pw.jsp">비밀번호 찾기</a>
		</div> 
		<%} else{ %>
		<div class="mem_outbox">
			<h3>입력하신 정보와 일치하는 비밀번호 : </h3>
			<%=pw %><br><br>
			<a href="#" class="login">로그인</a> 
			<a href="find_id.jsp" >아이디 찾기</a>
		</div>
		<%} %>
