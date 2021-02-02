<%@page import="beans.MemberDto"%>
<%@page import="beans.AdminDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	//관리자가 "회원"을 수정하는 것이므로, 회원 번호를 세션이 아닌 파라미터로 전달받아서 조회 결과를 출력해줘야 한다
	int member_no = Integer.parseInt(request.getParameter("member_no"));
	AdminDao adminDao = new AdminDao();
	MemberDto memberDto = adminDao.memberSearch(member_no);
%>    
    
<jsp:include page="/template/header.jsp"></jsp:include>

<form action="adminEdit.do" method="post">
	<!-- 회원번호는 표시하진 않지만 전송은 하도록 구현 -->
	<input type="hidden" name="member_no" value="<%=memberDto.getMember_no()%>">

	<div class="outbox" style="width:500px">
	
		<div class="row center">
			<h2>회원 정보 수정</h2>
		</div>
		
		<div class="row">
			<label>닉네임</label>
			<input type="text" name="member_nick" required class="input" 
						placeholder="한글 2~10자" value="<%=memberDto.getMember_nick()%>">
		</div>
		
		<%-- <div class="row">
			<label>권한</label>
			<select name="member_auth" class="input select"> 
				<option <%if(memberDto.is("일반")){%>selected<%}%>>일반</option>
				<option <%if(memberDto.is("우수")){%>selected<%}%>>우수</option>
				<option <%if(memberDto.is("관리자")){%>selected<%}%>>관리자</option>
			</select>
		</div> --%>
								
		<div class="row">
			<label>이메일</label>
			<input type="text" name="member_email" required 
						class="input" value="<%=memberDto.getMember_email()%>">
		</div>
			
				
		<div class="row">
			<input type="submit" value="정보수정" class="input">
		</div>
		
		<div class="row center">
			<a href="adminMemberList.jsp">목록으로 돌아가기</a>
		</div>
		
	</div>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>
