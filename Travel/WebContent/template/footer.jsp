<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<style>
	 	.footerList{
			list-style:none;
		}
		.footerList>li{
			margin:0.3rem;
		}
		.footerList>li>a{
			font-size:10px;
		}
		.footerList>li>a:hover{
			text-decoration: none;
			 cursor: pointer;
		}
		.box1, .box2, .box3{
			float:left;
			
		
		}
		.box1{
			width:40%;
		}
		.box2{
			width:40%;
		}
		.box3{
			width:20%;
		}
		
	</style>
	
	
	</section>
	
	
	<footer class="footer">
		<div class="box1">
			<ul class="footerList">
				<li><a>Policy</a></li>
				<li><a href="<%=request.getContextPath()%>/admin/reportList.jsp">FAQ</a></li>
				<li><a>Team</a></li>
				<li><a>함든솔 양세훈 박아현 노정권 안승철</a></li>
			</ul>
		</div>
		
		<div class="box2">
			<ul class="footerList">
				<li><a><img alt="인스타그램"src="<%=request.getContextPath()%>/adminImage/insta-icon.png" width="10px" style="margin-right:5px;">Instagram</a></li>
				<li><a><img alt="카카오톡"src="<%=request.getContextPath()%>/adminImage/kakao-talk.png" width="10px" style="margin-right:5px;">Kakaotalk</a></li>
				<li><a><img alt="구글플레이스토어"src="<%=request.getContextPath()%>/adminImage/playstore.png" width="10px" style="margin-right:5px;">Google</a></li>
				<li><a><img alt="애플앱스토어"src="<%=request.getContextPath()%>/adminImage/apple-icon.png" width="10px" style="margin-right:5px;">Apple</a></li>
			</ul>
		
		</div>
		
		<div class="footer-info box3">
			<ul class="footerList">
				<li><a> &copy;2021. 오늘의길</a></li>
				<li><a>Session ID : <%=session.getId()%></a></li>
				<li><a>check : <%=session.getAttribute("check")%></a></li>
				<li><a>auth : <%=session.getAttribute("auth")%></a></li>
			</ul>
		
			
		</div>	
	</footer>
</main>	
</body>
</html>