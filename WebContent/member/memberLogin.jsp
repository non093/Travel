<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/template/header.jsp"></jsp:include>

 <script>
    	$(function(){
    		var popupWidth = 500;
    		var popupHeight = 400;
    		var x =  (document.body.offsetWidth / 2) - (popupWidth / 2);
    		var y =  (window.screen.height /2) - (popupHeight / 2);
			
    		//띄어쓰기 금지
    		$("input").keydown(function(){
    	 	    if(event.keyCode === 32){
    	 	    	return false;
    	 	    }
    		});
    		
    		$("#find_id").click(function(e){
    			e.preventDefault();

    			url = "find_id.jsp";
    			open(url, "아이디 찾기","toolbar=no, menubar=no, scrollbar=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);//브라우저 중앙에 */
    		})
    		
    		$("#find_pw").click(function(e){
    			e.preventDefault();

    			url = "find_pw.jsp";
    			open(url, "비밀번호 찾기","toolbar=no, menubar=no, scrollbar=no,resizable=no, width="+popupWidth+",height="+popupHeight+", left="+x+", top="+y);//브라우저 중앙에 */
    		})
    	});
    </script>
  <style>
  
        .join{
        	float: left;
        }
        .find{
			 float: right;
        }  
       
      	.mem_input{
      		margin : 0 !important;
      		padding-left: 1rem;

      	}

		.login{
			font-size:10px;
			
		}
		.login-type>a{
			color:#AEB3B8;
	 	}
		
		.login-type:last-child{
			margin-left:100px;
			
	}
</style>

<form action="memberLogin.do" method="post">
<div class="outbox" style="width:400px">
	<div class="row center">
		<h2>로그인</h2>
	</div>
	<div class="row center login">
		<span class="login-type"><a href="../member/memberLogin.jsp">회원 로그인</a></span>
		<span class="login-type"><a href="../admin/adminLogin.jsp">관리자 로그인</a></span>
	</div>
	<div class="mem_row">
		
		<input type="text" name="member_id" class="mem_input" placeholder="ID" required>
	</div>
	<div class="mem_row">
		<input type=password name="member_pw" class="mem_input" placeholder="PASSWORD" required>
	</div>
	<%if(request.getParameter("error")!=null){ %>
	<div class="row" style="color:red;">
		입력하신 정보가 맞지 않습니다
	</div>
	<%}%>
	<div>
		<input type="submit" value="로그인" class="mem_input submit">
	</div>
	<div class="mem_row">
		<div class="join"><a href="join.jsp">회원가입</a></div>
		<div class="find"><a href="#" id="find_id" >아이디 찾기  |</a> <a href="#" id="find_pw">비밀 번호 찾기</a> </div>		
	</div>
</div>
</form>

<jsp:include page="/template/footer.jsp"></jsp:include>
