<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/template/header.jsp"></jsp:include>
			<h2 align="center">금주의 베스트 게시물</h2>
			<table align="center">
				 <tr>
				    <th><a href="<%=request.getContextPath()%>/index.jsp">
				    <img alt="게시물 이미지" src="http://placeimg.com/500/500/tech" style="width:200px; height:200px;">
				    </a></th>
				    <th><a href="<%=request.getContextPath()%>/index.jsp">
				    <img alt="게시물 이미지" src="http://placeimg.com/500/500/tech" style="width:200px; height:200px;">
				    </a></th>
				    <th><a href="<%=request.getContextPath()%>/index.jsp">
				    <img alt="게시물 이미지" src="http://placeimg.com/500/500/tech" style="width:200px; height:200px;">
				    </a></th>
				 </tr>
				 <tr>
				 	<td><a href="<%=request.getContextPath()%>/index.jsp">게시물1
				    </a></td>
				 	<td><a href="<%=request.getContextPath()%>/index.jsp">게시물2
				    </a></td>
				 	<td><a href="<%=request.getContextPath()%>/index.jsp">게시물3
				    </a></td>
				 </tr>
				 <tr>
				    <th><a href="<%=request.getContextPath()%>/index.jsp">
				    <img alt="게시물 이미지" src="http://placeimg.com/500/500/tech" style="width:200px; height:200px;">
				    </a></th>
				    <th><a href="<%=request.getContextPath()%>/index.jsp">
				    <img alt="게시물 이미지" src="http://placeimg.com/500/500/tech" style="width:200px; height:200px;">
				    </a></th>
				    <th><a href="<%=request.getContextPath()%>/index.jsp">
				    <img alt="게시물 이미지" src="http://placeimg.com/500/500/tech" style="width:200px; height:200px;">
				    </a></th>
				 </tr>
				 <tr>
				 	<td><a href="<%=request.getContextPath()%>/index.jsp">게시물4
				    </a></td>
				 	<td><a href="<%=request.getContextPath()%>/index.jsp">게시물5
				    </a></td>
				 	<td><a href="<%=request.getContextPath()%>/index.jsp">게시물6
				    </a></td>
				 </tr>
			</table>
<jsp:include page="/template/footer.jsp"></jsp:include>