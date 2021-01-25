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
<h1>프로필 이미지 업로드</h1>
 <%if(fileDto!=null) {%><!--설정한 이미지  -->
		<img src="download.do?member_no=<%=memberDto.getMember_no() %>" width=200px;><br>
		<%} else {%><!--설정하기전 기본 이미지 -->
		<img src="<%=request.getContextPath()%>/profile_img/basic.png" width=200px;><br>
		<%} %> 
 <form action ="upload.do" method="post" enctype="multipart/form-data">
 	<input type="file" name="f" accept=".gif, .png, .jpg">
 	<input type="submit" value="업로드">
  </form>
  <jsp:include page="/template/footer.jsp"></jsp:include>