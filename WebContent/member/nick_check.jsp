<%@page import="beans.MemberDto"%>
<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
  
   <script>
   		$(function(){
   			
   			<%
   			if(session.getAttribute("check") == null){ %>//check이름의 세션이 없다면==회원 가입할 때
 	   		 	 $(".submit").click(function(){// 사용 버튼을 누르면 join.jsp의 form안에 nick_checked의 값이 1로 바뀐다
 	   				opener.join.nick_checked.value="1";//부모창의 form[name=join]-hidden[name=nick_checked]의 value값을 1로 변경
	   	   			close();
	   			 }) 
	   		<% }  
   			
   		    if(session.getAttribute("check")!=null){ %>//check라는 이름을 가진 세션이 있다면 == 회원 정보 수정할 때
   				 $(".submit").click(function(){// 사용 버튼을 누르면 edit.jsp의 form안에 nick_checked의 값이 1로 바뀐다
   					opener.edit.nick_checked.value="1"; //부모창의 form[name=eidt]-hidden[name=nick_checked]의 value값을 1로 변경
   					close(); 
   				 })
   		    <% } %> 
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
	MemberDao memberDao = new MemberDao();
	String member_nick = request.getParameter("member_nick");
	boolean result = memberDao.nick_check(member_nick);

	if(session.getAttribute("check") == null){//회원가입할 때
		if(member_nick.equals("")){ %>
			<div class="mem_outbox">
				<h4>닉네임을 입력하세요</h4>
			</div>
		<%}else if(result){ %>
			<div class="mem_outbox">
				<h4 class="error">입력하신 "<%=member_nick %>"은(는) 이미 사용중인 닉네임입니다</h4>
			</div>
		<%} else { %>
			<div class="mem_outbox">
				<h4>입력하신 "<%=member_nick %>"은(는) 사용가능한 닉네임입니다</h4>
				<input type="button"  class="mem_input submit" value="사용">
			</div>
		<%} 
	} 

	if(session.getAttribute("check")!=null){//회원정보 수정일 때
		int member_no = (int)session.getAttribute("check");
		MemberDto memberDto = memberDao.find(member_no); 
		
		if(member_nick.equals("")){ %>
		<div class="mem_outbox">
			<h4>닉네임을 입력하세요</h4>
		</div>
		<%} else if(!memberDto.getMember_nick().equals(member_nick) && result){ %><!-- 새로 입력한 닉네임이 이미 있을 때 -->
			<div class="mem_outbox">
				<h4 class="error">입력하신 "<%=member_nick %>"은(는) 이미 사용중인 닉네임입니다</h4>
			</div>
			
		<%} else if(memberDto.getMember_nick().equals(member_nick) || !result){ %><!-- 본인의 닉네임이거나 중복된 닉네임이 없을 경우 -->
			<div class="mem_outbox">
				<h4>입력하신 "<%=member_nick %>"은(는) 사용가능한 닉네임입니다</h4>
				<input type="button" class="mem_input submit" value="사용">
			</div>
	 <%} 
	
	}%>  
	 