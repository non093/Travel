<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="travel.beans.*" %>

<%
// 	상세보기처럼 번호를 이용해서 글을 불러온다(조회수 증가는 하지 않음)
	int report_no = Integer.parseInt(request.getParameter("report_no"));
	ReportDao reportDao = new ReportDao();
	ReportDto reportDto = reportDao.find(report_no);
%>

<%
	String auth = (String)session.getAttribute("auth");
	boolean isAdmin = auth.equals("관리자");

%>


<jsp:include page="/template/header.jsp"></jsp:include>



<div class="outbox" style="width:450px">
	<div class="row center">
		<h2>신고 문의</h2>
	</div>
	<div class="row center">확인 요청</div>
	
	<form action="<%=request.getContextPath()%>/admin/reportEdit.do" method="post">
	
	<!-- 사용자 몰래 번호를 첨부 -->
	<input type="hidden" name="report_no" value="<%=reportDto.getReport_no()%>">
	<div>
		<input type="radio" name="report_header" value="<%=reportDto.getReport_header()%>" checked>
		<label><%=reportDto.getReport_header()%></label>
	</div>
	
	<div class="row">
		<label>제목</label>
		<input type="text" class="input" name="report_title" value="<%=reportDto.getReport_title()%>" required>
	</div>
	<div class="row">
		<label>내용</label>
		<textarea name="report_content" class="input textarea" required rows="10"><%=reportDto.getReport_content()%></textarea>
	</div>
	<div>
		<label>답변</label>
		<textarea name="report_answer" class="input textarea" required rows="10"></textarea>
	</div>
		
		<div class="row">
				<label>요청</label>
	
	<%if(isAdmin){ %>
		
			<%if(reportDto.getReport_qa() > 0){ %>
			<div>
				<input type="radio" name="report_qa"  value=0>
				<label>확인중으로 변경</label>
			</div>	
			<%}else{%>		
			<div>
				<input type="radio" name="report_qa" checked  value=1>
				<label>확인</label>
			</div>	
			<%}%>
	<%}else{ %>
			<div>
				<input type="radio" name="report_qa" checked value=0>
				<label>확인</label>
			</div>	
	<%}%>
	</div>	
	
	<div class="row">
		<input type="submit" class="input" value="수정">
	</div>
	 
	</form>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>