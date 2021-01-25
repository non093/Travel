<%@page import="beans.FileDto"%>
<%@page import="beans.FileDao"%>
<%@page import="beans.MemberDto"%>
<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:include page="/template/header.jsp"></jsp:include>
     <%
    	int member_no = (int)session.getAttribute("check");
    	MemberDao memberDao = new MemberDao();
    	MemberDto memberDto = memberDao.find(member_no);
 		FileDao fileDao = new FileDao();
		FileDto fileDto = fileDao.find(member_no); 
    %> 
    <script>
    	$(function(){
    		$(".delete").click(function(e){
    			e.preventDefault();
    			if(confirm("탈퇴하시겠습니까?")){
    				location.href="delete.do?member_no=<%=member_no%>";
    			}
    		}); 
    	
    	});
    </script>
    
   
<style>
    	.detail{
    		width : 400px;
    	}
    	input{
    		width : 400px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
	<div class="detail">
	<h1>회원 상세정보</h1>   
		<%if(fileDto!=null) {%><!--설정한 이미지  --> 
		<img src="download.do?member_no=<%=memberDto.getMember_no() %>" width=200px;><br>
		<%} else {%><!--설정하기전 기본 이미지 -->
		<img src="<%=request.getContextPath()%>/profile_img/basic.png" width=200px;><br>
		<%} %> 
		<label>아이디 : <%=memberDto.getMember_id()%></label><br>
		<label>닉네임 : <%=memberDto.getMember_nick()%></label><br>
		<label>이메일 : <%=memberDto.getMember_email()%></label><br>
		<label>가입일 : <%=memberDto.getMember_date() %></label><br>
		<a href="#">내 글 보기</a><br>
		<a href="#">좋아요 한 글 보기</a><br>
		<a href="edit.jsp">정보 수정</a><br> 
		<a href="delete.do" class="delete">탈퇴하기</a>
		
	</div>
	<jsp:include page="/template/footer.jsp"></jsp:include>