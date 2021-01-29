<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
		</section>
		<footer>
		<h5 class="center">회사 정보 &copy;</h5>
		session ID : <%=session.getId() %><br>
		check : <%=session.getAttribute("check") %> <br>
 		auth : <%=session.getAttribute("auth") %>
 		</footer>
	</main>
	
</body>
</html> 

