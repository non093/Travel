<%@page import="beans.FileDto"%>
<%@page import="beans.FileDao"%>
<%@page import="beans.MemberDao"%>
<%@page import="beans.MemberDto"%>
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
  <style>
    	.edit{
    		width : 600px;
    	}
    	input{
    		width : 500px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
<script>
$(function(){
	var popupWidth = 500;
	var popupHeight = 200;
	var x =  (document.body.offsetWidth / 2) - (popupWidth / 2);
	var y =  (window.screen.height /2) - (popupHeight / 2);

	$(".nick-btn").click(function(){//닉네임 중복체크
		var nick = document.querySelector(".nick").value;
		
		url = "nick_check.jsp?member_nick="+nick;
		open(url, "닉네임 중복체크","toolbar=no, menubar=no, scrollbar=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);
		return false;

	
		});
	
	//중복확인 후, 추가 입력시 중복체크 한번 더 하기
 	$(".nick").keyup(function(){ 
  		$(".nick_checked").val("0"); 
	}); 
	
	$(".submit").click(function(){//회원 가입 버튼 눌렀을 때
		var nick_checked = $(".nick_checked").val();
		var pw = $(".pw").val();
		var email = $(".email").val();
		
		if(nick_checked=="0"){
			alert("닉네임 중복확인 하세요");
			return false;
		}
		if(pw==0 || email==0){//나머지 정보를 입력 안했을시
			alert("정보를 입력하세요");
			return false;
		}
		else{//아이디,닉네임 중복 확인이랑 나머지 정보를 입력완료 했을 때
			return true;
		}
		
	});
	
});
</script>
<form action="edit.do" method="post" name="edit">
	<div class="edit">
	
		<h1>회원정보 수정</h1>
		<%if(fileDto!=null) {%><!--설정한 이미지  --> 
			<img src="download.do?member_no=<%=memberDto.getMember_no() %>" width=200px;><br>
			<%} else {%><!--설정하기전 기본 이미지 -->
			<img src="<%=request.getContextPath()%>/profile_img/basic.png" width=200px;><br>
			<%} %> 
		<a href="editPW.jsp">비밀번호 변경</a>
		<a href="upload.jsp">프로필 이미지</a>
		<div>
			ID <input type="text" name="member_id" value="<%=memberDto.getMember_id() %>" readonly>
		</div>
		
		<div>
			NICK <input type="text" name="member_nick" value="<%=memberDto.getMember_nick() %>" class="nick">
			<button class="nick-btn">닉네임 중복 확인</button>
			<input type="hidden" class="nick_checked" name="nick_checked" value="2">
			<!-- value가 0 = 중복체크해야됨| 1 = 중복체크 통과 | 2 = 원래 닉네임-->
		 
		</div>
		
		<div>
			EMAIL <input type="text" name="member_email" class="email" value="<%=memberDto.getMember_email() %>">
		</div>
		
		<div>
			<input type="password" name="member_pw" class="pw" placeholder="비밀번호 입력" required>
		</div>
		<%if(request.getParameter("error")!=null){ %>
		<label style="color:red">비밀번호가 맞지않습니다</label>
		<%} %>
		<input type="submit" value="정보 수정" class="submit">
	</div>
</form>
<jsp:include page="/template/footer.jsp"></jsp:include>