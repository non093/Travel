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
    	.editPW{
    		width : 400px;
    	}
    	input{
    		width : 400px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
	<div class="editPW">
	<h1>비밀번호 변경</h1>
	
		<form action = "editPW.do" method="post">
		<label>ID : <%=memberDto.getMember_id()%></label><br>
		<input type="password" name="origin_pw" placeholder="현재 비밀번호 입력" required>
		<input type="password" name="change_pw" placeholder="새 비밀번호 입력" required>
		<%if(request.getParameter("error")!=null){ %>
		<label style="color:red">현재 비밀번호가 일치하지않습니다</label>
		<%} %>
		<input type="submit" value="비밀번호 수정">
		
		
		</form>
		
	</div>
	<jsp:include page="/template/footer.jsp"></jsp:include>