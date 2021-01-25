<%@page import="beans.MemberDto"%>
<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%
  	
  %>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
   <script>
   		$(function(){
   			<%
   			if(session.getAttribute("check") == null){ %>//check이름의 세션이 없다면==회원 가입할 때
 	   		 	 $("#ok").click(function(){// 사용 버튼을 누르면 join.jsp의 form안에 nick_checked의 값이 1로 바뀐다
 	   				opener.join.nick_checked.value="1";//부모창의 form[name=join]-hidden[name=nick_checked]의 value값을 1로 변경
	   	   			close();
	   			 }) 
	   		<% }  
   			
   		    if(session.getAttribute("check")!=null){ %>//check라는 이름을 가진 세션이 있다면 == 회원 정보 수정할 때
   				 $("#ok").click(function(){// 사용 버튼을 누르면 edit.jsp의 form안에 nick_checked의 값이 1로 바뀐다
   					opener.edit.nick_checked.value="1"; //부모창의 form[name=eidt]-hidden[name=nick_checked]의 value값을 1로 변경
   					close(); 
   				 })
   		    <% } %> 
   			
   		});
    </script>
     <style>
    	h4{
    		text-align: center;
    	}
    </style>
 
	
<% 
	MemberDao memberDao = new MemberDao();
	String member_nick = request.getParameter("member_nick");
	boolean result = memberDao.nick_check(member_nick);

	if(session.getAttribute("check") == null){//회원가입할 때
		if(member_nick.equals("")){ %>
			<br><br>
			<h4>닉네임을 입력하세요</h4>
		<%}else if(result){ %>
			<br><br>
			<h4 style="color:red">입력하신 "<%=member_nick %>"은(는) 이미 사용중인 닉네임입니다</h4>
		<%} else { %>
			<br><br>
			<h4>입력하신 "<%=member_nick %>"은(는) 사용가능한 닉네임입니다</h4>
			<input type="button" id="ok" value="사용">
		<%} 
	} 

	if(session.getAttribute("check")!=null){//회원정보 수정일 때
		int member_no = (int)session.getAttribute("check");
		MemberDto memberDto = memberDao.find(member_no); 
		
		if(member_nick.equals("")){ %>
		<br><br>
		<h4>닉네임을 입력하세요</h4>
		<%} else if(!memberDto.getMember_nick().equals(member_nick) && result){ %><!-- 새로 입력한 닉네임이 이미 있을 때 -->
			<br><br>
			<h4 style="color:red">입력하신 "<%=member_nick %>"은(는) 이미 사용중인 닉네임입니다</h4>
			
		<%} else if(memberDto.getMember_nick().equals(member_nick) || !result){ %><!-- 본인의 닉네임이거나 중복된 닉네임이 없을 경우 -->
			<br><br>
			<h4>입력하신 "<%=member_nick %>"은(는) 사용가능한 닉네임입니다</h4>
			<input type="button" id="ok" value="사용">
	 <%} 
	
	}%>  
	 