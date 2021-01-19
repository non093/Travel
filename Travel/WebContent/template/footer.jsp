<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

		</section>
		<footer>
			<h5 class="center">&copy;세미 프로젝트 홈페이지 </h5>
			
			<h5 class="center">Session ID : <%=session.getId()%></h5>
			<h5 class="center">check : <%=session.getAttribute("check")%></h5>
			<h5 class="center">auth : <%=session.getAttribute("auth")%></h5>
		</footer>
	</main>
</body>
</html>