<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	</section>
	</article>
	<footer>
			<h5 class="center">회사 정보 &copy;</h5>
			
			<h5 class="center">Session ID : <%=session.getId()%></h5>
			<h5 class="center">check : <%=session.getAttribute("check")%></h5>
			<h5 class="center">auth : <%=session.getAttribute("auth")%></h5>
	</footer>
</body>
</html>