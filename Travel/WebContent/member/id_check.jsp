<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
 
     <script>
   		$(function(){
   			$(".submit").click(function(){/* 사용 버튼을 누르면 join.jsp의 form안에 id_checked의 값이 1로 바뀐다 */
   	   			opener.join.id_checked.value="1";
   	   			close();
   			})
   			$(document).keyup(function(e) {//esc누르면 팝업창 닫기
   		     	if(e.keyCode == 27) { 
   		    		close();
   		   	 	}
   			})
   		});
    </script>
    <style>
   		html {
    			overflow:hidden;
    	}
    	.mem_outbox{
    		margin: 5rem auto;
    		text-align: center;
    	}
    	
    </style>
 
     
<% 
	String member_id = request.getParameter("member_id");
	MemberDao memberDao = new MemberDao(); 
	boolean result = memberDao.id_check(member_id);
	
	if(member_id.equals("")){ %>
		<div class="mem_outbox">
			<h4>아이디를 입력하세요</h4>
		</div>
	<%}else if(result){ %>
		<div class="mem_outbox">
			<h4 class="error">입력하신 "<%=member_id %>"은(는) 이미 사용중인 아이디입니다</h4>
		</div>
	<%} else { %>
		 <div class="mem_outbox">
			<h4>입력하신 "<%=member_id %>"은(는) 사용가능한 아이디입니다</h4>
			<input type="button" class="mem_input submit" value="사용">
		</div>
	<%} %>