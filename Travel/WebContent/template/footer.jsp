<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
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
</html>  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	</section>
	</article>
	<footer>
		<table width="100%";>
			<tr>
				<th>
					<ul>
						<li align="left">Policy</li>
						<li align="left"><a href="<%=request.getContextPath()%>/board/board.jsp">FAQ</a></li>
						<li align="left">Team</li>
						<li align="left">함든솔, 양세훈, 박아현, 노정권, 안승철</li>
					</ul>
				</th>
				<th>
					<ul>
						<li align="left">Instagram</li>
						<li align="left">Kakaotalk</li>
						<li align="left">Facebook</li>
						<li align="left">Google</li>
					</ul>	
				</th>
				<th>
					<ul>
						<li align="left">
							&copy;2021. 오늘의 길
						</li>
						<li align="left">
							Session ID : <%=session.getId()%>
						</li >
						<li align="left">
							check : <%=session.getAttribute("check")%>
						</li>
						<li align="left">
							<h5>auth : <%=session.getAttribute("auth")%></h5>
						</li>
					</ul>
				</th>
			</tr>
		</table>
		<hr>
	</footer>
</main>
</body>
</html>