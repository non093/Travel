<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="travel.beans.AdminDao"%>
<%@page import="travel.beans.MemberDto"%>

<jsp:include page="/template/header.jsp"></jsp:include>

<%
	// 관리자 계정으로 회원정보 불러오기
	
	int member_no = Integer.parseInt(request.getParameter("member_no"));
	AdminDao adminDao = new AdminDao();
	MemberDto memberDto = adminDao.find(member_no); 
%>


<div class="outbox" style="width:400px">
	<div class="row center">
		<h2>회원 정보</h2>
	</div>
	<div class="row">
		<table class="table table-border">
			<tbody>
				<tr>
					<th width="25%">번호</th>
					<td><%=memberDto.getMember_no()%></td>
				</tr>
				<tr>
					<th>프로필 사진</th>
					<td height="60px"><%=memberDto.getMember_image()%></td>
				</tr>
				<tr>
					<th>아이디</th>
					<td><%=memberDto.getMember_id()%></td>
				</tr>
				<tr>
					<th>닉네임</th>
					<td><%=memberDto.getMember_nick()%></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><%=memberDto.getMember_email()%></td>
				</tr>
				<tr>
					<th>가입일</th>
					<td><%=memberDto.getMember_date()%></td>
				</tr>
				
			</tbody> 
		</table>
	</div>
	
	
	<div class="row center">
		<a href="list.jsp">목록으로 돌아가기</a>
	</div>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>