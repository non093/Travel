<%@page import="beans.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <jsp:include page="/template/header.jsp"></jsp:include>
    

<script>
	$(function(){
		$(document).ready(function(){  
			  $(document).bind('keydown',function(e){
			    if ( e.keyCode == 123 /* 키코드 123 = F12 */) {
			    	e.preventDefault();
			    	e.returnValue = false;
			    }
			  });
			});
		var popupWidth = 600;
		var popupHeight = 250;
		var x =  (document.body.offsetWidth / 2) - (popupWidth / 2);
		var y =  (window.screen.height /2) - (popupHeight / 2);
		
		
		$("#id_check").click(function(){//아이디 중복체크
			var id = $(".id").val();

			url = "id_check.jsp?member_id="+id;
			open(url, "아이디 중복체크","toolbar=no, menubar=no, scroll=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);//브라우저 중앙에 */
			return false;
 		});
		
		$("#nick_check").click(function(){//닉네임 중복체크
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
	 	
	 	//띄어쓰기 금지
		$("input").keydown(function(){
	 	    if(event.keyCode === 32){
	 	    	return false;
	 	    }
		});
	 	//비밀번호 재확인
	 	$(".error").hide();//오류 메세지 첨에 안보이게
	 	$(".pw1, .pw2").keyup(function(){
	 		var pw1 = $(".pw1").val();
			var pw2 = $(".pw2").val();
			 
	 		if(pw1 && pw1 != pw2){//pw값이 있고 pw2값이 있고 둘이 같지않다면
	 			$(".error").show();
	 		 	$(".pw_checked").val("1");
	 		 	return false;
	 		}
	 		else{//일치
	 		 	$(".error").hide();
	 		 	$(".pw_checked").val("0");
	 		 	return true;
	 		}
	 	}); 
	 
		$(".submit").click(function(e){//회원 가입 버튼 눌렀을 때
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
         .mem_input {
             border : none !important;
        }

        .mem_input:focus {
            border-bottom: 1px solid lightgray;
        }
        .id,
        .nick {
            width: 300px;
        }
        .btn{
        	width : 70px;
        }
         
  		.error{
  			margin-left : 0.5rem;
  		}
    </style>
<form action="join.do" method="post" name="join">
	<div class="mem_outbox">
	  
		<div class="mem_row">
            <input type="text" name="member_id" placeholder="아이디 입력" class="mem_input id">
            <input type="hidden" class="id_checked" name="id_checked" value="0">
            <!-- 중복체크필요하거나 중복됨 0, 중복되지 않으면 1 -->
            <button class="btn btn_hover" id="id_check">중복 확인</button>
        </div>
	  	<div class="mem_row">
            <input type="password" name="member_pw" placeholder="비밀번호 입력" class="mem_input pw1">
        </div>
        <div class="mem_row">
            <input type="password" name="member_pw" placeholder="비밀번호 재입력" class="mem_input pw2">
            <input type="hidden" class="pw_checked" value="0">
            <!--일치: 0, 비밀번호가 서로 일치하지않음: 1  -->
        </div>
	            <label class="error">비밀번호가 일치하지않습니다</label>
		<div class="mem_row">
            <input type="text" name="member_nick" placeholder="닉네임 입력" class="mem_input nick">
            <input type="hidden" class="nick_checked" name="nick_checked" value="0">
			<!-- 중복체크필요하거나 중복됨 0, 중복되지 않으면 1 -->
    		<button class="btn btn_hover" id="nick_check">중복 확인</button>
        </div>
        <div class="mem_row">
			<input type="text" class="mem_input email" name="member_email" placeholder="이메일">
        </div>
        <div class="mem_row">
            <input class="mem_input submit" type="submit" value="회원가입">
        </div>
	</div>
	
</form>
  <jsp:include page="/template/footer.jsp"></jsp:include>
  
