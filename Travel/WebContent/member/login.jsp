<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>     
 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/member.css">
 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
 
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
      		padding-left: 1rem;

      	}
    </style>
<form action="login.do" method="post">
	<div class="mem_outbox">
	<div>
      <a href="<%=request.getContextPath()%>"><img alt="로고" class="logo_img" src="<%=request.getContextPath()%>/image/logo.png" ></a>
    </div>
    <div class="mem_row">
		<input type="text" name="member_id" class="mem_input" placeholder="아이디" required>
	</div>
	<div class="mem_row">	
		<input type="password" name="member_pw" class="mem_input" placeholder="비밀번호" required>
	</div>
			
		<%if(request.getParameter("error")!=null){ %>
		<label class="error">입력하신 정보가 맞지않습니다</label>
			<%} %>
		

	<div class="mem_row">
		<input type="submit" class="mem_input submit" value="로그인">
	</div>
	<div class="mem_row">
		<div class="join"><a href="join.jsp">회원가입</a></div>
		<div class="find"><a href="#" id="find_id" >아이디 찾기  |</a> <a href="#" id="find_pw">비밀 번호 찾기</a> </div>		
	</div>
	</div>
</form>
