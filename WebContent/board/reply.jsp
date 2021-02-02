<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 답변</title>
<script>
	function check() {
		opener.document.location.reload();
	}
</script>
<style>
	.box {
		width:600px;
		margin; 0 auto 0 auto;
		text-align:center;
	}
	.replyform {
		text-align:center;
	}
	.btn {
		margin: 0px auto;
	}  

</style>
<script>
</script>
</head>
<body>
	<div class="box">
	
	<font size="5" color="blue">댓글 답변</font>
	<%= request.getParameter("reply_no") %>
	
	<div class="replyform">
		<form class="rereplyform" action="rereply_insert.do" method="POST">
		<input type="hidden" name="reply_no" value="<%= request.getParameter("reply_no") %>">
		<input type="hidden" name="reply_board" value="<%= request.getParameter("reply_board") %>">
		<textarea name="reply_content" style="width:400px; height:100px;"></textarea>
		<br><br>
		<input class="btn" type="submit" value="등록" onclick="check()" >
		<input class="btn" type="button" value="취소" onclick="window.close()">
		</form> 
	</div>
	</div>
</body>
</html>