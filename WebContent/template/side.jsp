<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="beans.*" %>
    <%@ page import="util.*" %>
    <%
    VisitCountDAO visitCountDAO = new VisitCountDAO();
	
	int todayCount = 0;
    int totalCount = 0;
    
	    String visit = session.getId();
		
	    boolean isOverlap = visitCountDAO.visitCheck(visit);
	    
	    if(isOverlap){
			visitCountDAO.setVisitTotalCount(visit);
	    } %>

		<aside>
		<div align="right" class="menuBtn">
        		<p>&equiv;닫힘</p>
        		<p style="display:none;">&equiv;열림</p>
        </div>
		<div class="side-style" id="menuArea">
			<li><a href="<%=request.getContextPath()%>/board/entirelist.jsp?cate=전체">전체 게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/board/noticelist.jsp?cate=공지">공지 게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/board/freelist.jsp?cate=자유">자유 게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/board/travellist.jsp?cate=여행">여행 게시판</a></li>
			<li style="font-size:10px;">전체 방문자 : <%=visitCountDAO.getVisitTotalCount()%></li>
			<li style="font-size:10px;">오늘 방문자 : <%=visitCountDAO.getVisitTodayCount()%></li>
		</div>	
		</aside>
		<section>