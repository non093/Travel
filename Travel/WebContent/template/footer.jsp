<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		
		</section>
		<footer>
		세션 ID : <%=session.getId() %><br>
		check : <%=session.getAttribute("check") %> <br>
		auth : <%=session.getAttribute("auth") %>
		</footer>
	</main>
	
</body>
</html> 
