<%@page import="beans.FileDto"%>
<%@page import="beans.FileDao"%>
<%@page import="beans.MemberDto"%>
<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
    
    <%
    	int member_no = (int)session.getAttribute("check");
    	MemberDao memberDao = new MemberDao();
    	MemberDto memberDto = memberDao.find(member_no);
    	FileDao fileDao = new FileDao();
		FileDto fileDto = fileDao.find(member_no); 
    %>
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script>
    	$(function(){
    		$("#submit").click(function(){ 
        		opener.location.reload(); 
        		close();//확인 버튼 누르면 팝업닫고 정보수정창으로
     		 })
    	});
    	
    </script>
	 
 <div style="text-align:center">
 <%if(fileDto!=null) {%><!--설정한 이미지  -->
		<img alt="프로필 이미지" class="profile_img" src="download.do?member_no=<%=memberDto.getMember_no() %>"><br>
		<%} else {%><!--설정하기전 기본 이미지 -->
		<img alt="프로필 이미지" class="profile_img" src="<%=request.getContextPath()%>/profile_img/basic.png"><br>
		<%} %> 
		
	 <form action ="upload.do" method="post" enctype="multipart/form-data">
	 	<div style="margin : 2rem 0">
		 	<input type="file" name="f" accept=".gif, .png, .jpg">
		 	<input type="submit"  value="업로드">
		 	<input type="button" id="submit" value="확인">
 		</div>
  </form>
 </div>
