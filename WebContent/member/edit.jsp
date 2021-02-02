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
  
<script>
$(function(){
	var popupWidth = 500;
	var popupHeight;
	

	$(".nick_btn").click(function(){//닉네임 중복체크
		popupHeight = 250;
		var x =  (document.body.offsetWidth / 2) - (popupWidth / 2);
		var y =  (window.screen.height /2) - (popupHeight / 2);
		
		var nick = $(".nick").val();
		
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
	//엔터쳤을 때 submit 방지
 	$("input[type=text], input[type=password], button").keydown(function(){
 	    if(event.keyCode === 13)
 	        event.preventDefault();
 	});
	$(".edit_img").click(function(e){
		popupHeight = 400;
		var x =  (document.body.offsetWidth / 2) - (popupWidth / 2);
		var y =  (window.screen.height /2) - (popupHeight / 2);

		e.preventDefault();

		url = "upload.jsp?member_no=?"+<%=member_no %>;
		open(url, "프로필 변셩","toolbar=no, menubar=no, scrollbar=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);//브라우저 중앙에 */
		return false;
	})

	
	
});
</script>
<style> 
         .mem_input{
    		margin : 0 !important;
     	 } 
 
        a{ 
            margin-left : 2rem;
        }
        a:hover{
            color: gray;
        }
  		 
        .btn{
       		height: auto;
        }
 
        .edit{
        	float : right;
        }
       
 </style>
 
<form action="edit.do" method="post" name="edit">
	<div class="mem_outbox">
		<div class="mem_row">
			<%if(fileDto!=null) {%><!--설정한 이미지  --> 
			<img alt="프로필 이미지" class="profile_img" src="download.do?member_no=<%=memberDto.getMember_no() %>"><br>
			<%} else {%><!--설정하기전 기본 이미지 -->
			<img alt="프로필 이미지" class="profile_img" src="<%=request.getContextPath()%>/profile_img/basic.png"><br>
			<%} %>  
			<div class="edit">
				<a href="#" class="edit_img">사진 변경</a> 
				<a href="editPW.jsp">비밀번호 변경</a>
			</div>
		</div>
		<div class="mem_row">
			<label>ID</label><br> 
			<div><%=memberDto.getMember_id() %></div>
		</div>
		
		<div class="mem_row">
			<div  >
			<label>NICK</label>
			<button class="btn btn_hover nick_btn">중복 확인</button></div>
			<input type="text" name="member_nick" value="<%=memberDto.getMember_nick() %>" class="mem_input nick">
			<input type="hidden" class="nick_checked" name="nick_checked" value="2">
			<!-- value가 0 = 중복체크해야됨| 1 = 중복체크 통과 | 2 = 원래 닉네임-->
		 
		</div>
		
		<div class="mem_row">
			<label>EMAIL</label>
			<input type="text" name="member_email" class="mem_input email" value="<%=memberDto.getMember_email() %>">
		</div>
		
		<div class="mem_row">
			<input type="password" name="member_pw" class="mem_input pw" placeholder="비밀번호 입력" required>
		</div>
		<%if(request.getParameter("error")!=null){ %>
		<label class="error">비밀번호가 일치하지않습니다</label>
		<%} %>
		<div class="mem_row">
			<input type="submit" value="정보 수정" class="mem_input submit">
		</div>
	</div>
</form>
  <jsp:include page="/template/footer.jsp"></jsp:include>
 