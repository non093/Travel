<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
   <script>
   		$(function(){
   			$("#ok").click(function(){/* 사용 버튼을 누르면 join.jsp의 form안에 id_checked의 값이 1로 바뀐다 */
   	   			opener.join.id_checked.value="1";
   	   			window.close();
   			})

   		})
    </script>
    <style>
    	h4{
    		text-align: center;
    	}
    </style>
     
<% 
	String member_id = request.getParameter("member_id");
	MemberDao memberDao = new MemberDao(); 
	boolean result = memberDao.id_check(member_id);
	
	if(member_id.equals("")){ %>
		<br><br>
		<h4>아이디를 입력하세요</h4>
	<%}else if(result){ %>
		<br><br>
		<h4 style="color:red">입력하신 "<%=member_id %>"은(는) 이미 사용중인 아이디입니다</h4>
	<%} else { %>
		<br><br>
		<h4>입력하신 "<%=member_id %>"은(는) 사용가능한 아이디입니다</h4>
		<input type="button" id="ok" value="사용">
		
	<%} %>