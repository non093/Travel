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
    
       a{ 
           margin : 2rem 0;
       }
       a:hover{
           color: gray;
       }
      
       #edit{
            float: right; 
       }

</style>
	<div class="mem_outbox">
	<h1>My Page</h1>   
		<%if(fileDto!=null) {%><!--설정한 이미지  --> 
		<img alt ="프로필 이미지" src="download.do?member_no=<%=memberDto.getMember_no() %>"  class="profile_img">
		<%} else {%><!--설정하기전 기본 이미지 -->
		<img alt ="기본 프로필 이미지" src="<%=request.getContextPath()%>/profile_img/basic.png"  class="profile_img">
		<%} %> 
		<div class="mem_row">
                <div>
                    <label>아이디</label>
                    <div id="edit">
                        <a href="edit.jsp">정보 수정</a>
                    </div>
                </div>              
                <div><%=memberDto.getMember_id()%></div>
            </div>
            <div class="mem_row">
                <label>닉네임</label><br>
                <div><%=memberDto.getMember_nick()%></div>
            </div>
            <div class="mem_row">		
                <label>이메일</label><br>
                <div><%=memberDto.getMember_email()%></div>
            </div>
            <div class="mem_row">		
                <label>가입일</label><br>
                <div><%=memberDto.getMember_date() %></div>
            </div>
            <div class="mem_row">
                <div class="mem_menu"><a href="#">내 글 보기</a></div>
                <div class="mem_menu"><a href="#">좋아요 한 글 보기</a></div>
                <div class="mem_menu"><a href="delete.do" class="delete">탈퇴하기</a></div>
            </div>
		
	</div>
<jsp:include page="/template/footer.jsp"></jsp:include>