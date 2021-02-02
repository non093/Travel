<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
     <script>
    	$(function(){
    		$(document).ready(function() { 
    			if(confirm("회원가입 성공\n로그인 하시겠습니까?")){
    				
				location.replace("<%=request.getContextPath()%>/member/memberLogin.jsp");
				}
				else{
				location.replace("<%=request.getContextPath()%>");
				} 
    		});		
    	});
    </script>
