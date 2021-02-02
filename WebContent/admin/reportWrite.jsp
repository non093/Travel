<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*" %>




<jsp:include page="/template/header.jsp"></jsp:include>

<script>
	$(function(){
		$(".list-btn").click(function(){
			//location.href = "list.jsp";//상대경로
			location.href = "<%=request.getContextPath()%>";
		});
	});
</script>

<div class="outbox" style="width:400px">
	<button class="input input-inline list-btn" style="margin-left:355px;">목록</button>
	
	<div class="row center">
		<h2>문의</h2>
	</div>
	
	<form action="reportWrite.do" method="post">
	
	<div>
		<input type="radio" name="report_header"  value="일반" checked>
		<label>일반</label>
	</div>
	
	
	<div class="row">
		<input type="text" class="input" name="report_title" placeholder="제목을 입력하세요" required>
	</div>
	
	<div class="row">
		<textarea name="report_content" class="input textarea" required rows="10">내용을 입력하세요</textarea>  
			
		
	</div>
	
	<div class="row">
		<input type="submit" class="input" value="보내기">
	</div>
	 
	</form>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>