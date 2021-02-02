<%@page import="beans.MemberDto"%>
<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:include page="/template/header.jsp"></jsp:include>
 
<%
    	int member_no = (int)session.getAttribute("check");
    	MemberDao memberDao = new MemberDao();
    	MemberDto memberDto = memberDao.find(member_no);

    %>
    
 <style>
    	 .mem_input{
    		margin : 0 !important;
    	 	padding-left : 1rem;
    	 }
    		
         
</style>
	
	
<form action = "editPW.do" method="post">
	<div class="mem_outbox">
		
		<div class="mem_row">
			<input type="password" class="mem_input" name="origin_pw" placeholder="현재 비밀번호 입력" required>
		</div>
		<div class="mem_row">
			<input type="password" class="mem_input" name="change_pw" placeholder="새 비밀번호 입력" required>
		</div>
		<div class="mem_row">
			<%if(request.getParameter("error")!=null){ %>
			<label class="error">현재 비밀번호가 일치하지않습니다</label>
			<%} %>
		</div>
		<div class="mem_row">
			<input type="submit" class="mem_input submit" value="비밀번호 수정">
		</div>
	</div>
</form>
	<jsp:include page="/template/footer.jsp"></jsp:include>	
 