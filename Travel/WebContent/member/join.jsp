<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<jsp:include page="/template/header.jsp"></jsp:include>

<script>
	$(function(){
		var popupWidth = 500;
		var popupHeight = 200;
		var x =  (document.body.offsetWidth / 2) - (popupWidth / 2);
		var y =  (window.screen.height /2) - (popupHeight / 2);
		 
  
		
		$(".id_btn").click(function(){//아이디 중복체크
			var id = $(".id").val();

			url = "id_check.jsp?member_id="+id;
			open(url, "아이디 중복체크","toolbar=no, menubar=no, scrollbar=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);//브라우저 중앙에 */
			return false;
 		});
		
		$(".nick_btn").click(function(){//닉네임 중복체크
			var nick = $(".nick").val();
			
			url = "nick_check.jsp?member_nick="+nick;
			open(url, "닉네임 중복체크","toolbar=no, menubar=no, scrollbar=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);
			return false;

		
 		});
		
		//중복확인 후, 추가 입력시 중복체크 한번 더 하기
	 	$(".id").keyup(function(){//아이디 중복 확인 후, 추가 입력하면
	  		$(".id_checked").val("0");//id_checked의 vlaue가 0으로 돌아가기땜에 다시 한번 중복 확인을 해야함
		}); 
	 	$(".nick").keyup(function(){ 
	  		$(".nick_checked").val("0"); 
		}); 
	 	
	 	//비밀번호 재확인
	 	$(".pw_ok").hide();
	 	$(".pw_no").hide();

	 	$(".pw2").keyup(function(){
	 		if($(".pw1").val() != $(".pw2").val()){//비밀번호 두 개가 일치하지않으면
	 			$(".pw_no").show();
	 		 	$(".pw_ok").hide();
	 		 	$(".pw_checked").val("1");
	 		 	return false;

	 		}
	 		else{
	 			$(".pw_ok").show();
	 		 	$(".pw_no").hide();
	 		 	$(".pw_checked").val("0");
	 		 	return true;
	 		}
	 	});
				
		$(".submit").click(function(){//회원 가입 버튼 눌렀을 때
			if($(".id").val()==0 || $(".nick").val()==0 || $(".pw1").val()==0 || $(".pw2").val()==0 || $(".email").val()==0){
				alert("정보를 입력하세요");
				return false;
			}
			if($(".id_checked").val()=="0"){//중복 확인을 안함
				alert("아이디 중복확인 하세요");
				return false;
			}
	
			if($(".nick_checked").val()=="0"){
				alert("닉네임 중복확인 하세요");
				return false;
			}
			if($(".pw_checked").val()=="1"){//중복 확인을 안함
				alert("비밀번호가 일치하지않습니다");
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

 	});
	
	
</script> 
    <style>
    	.join{
    		width : 400px;
    	}  
    	input{
    		width : 400px;
    		height : 40px;
    		margin : 0.5rem;
    	}
    </style>
<form action="join.do" method="post" name="join" id="join_form">
	<div class="join">
	    <h1>회원가입</h1>
		
		<input type="text" name="member_id" placeholder="아이디 입력" class="id" >
		<input type="hidden" class="id_checked" name="id_checked" value="0"><!-- 중복체크필요하거나 중복됨 0, 중복되지 않으면 1 -->
		<button class="id_btn">아이디 중복 확인</button>
		
		<input type="password" name="member_pw" placeholder="비밀번호 입력"  class="pw1">
		<input type="password" name="member_pw" placeholder="비밀번호 재입력"  class="pw2">
		<input type="hidden" class="pw_checked" value="0"><!--일치: 0, 비밀번호가 서로 일치하지않음: 1  -->
		<label class="pw_ok">비밀번호가 일치합니다</label>
		<label class="pw_no" style="color:red">비밀번호가 일치하지않습니다</label>
		
		
		<input type="text" name="member_nick" placeholder="닉네임 입력" class="nick"  >
		<input type="hidden" class="nick_checked" name="nick_checked" value="0"><!-- 중복체크필요하거나 중복됨 0, 중복되지 않으면 1 -->
		<button class="nick_btn">닉네임 중복 확인</button>
		
		<input type="text" name="member_email" placeholder="이메일 입력" class="email">
		
		
		<input type="submit" value="회원가입" class="submit">
	</div>
</form>
<jsp:include page="/template/footer.jsp"></jsp:include>