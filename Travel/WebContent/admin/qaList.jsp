<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="travel.beans.*" %>

<%
	request.setCharacterEncoding("UTF-8");
%>

<%
// 	페이지 분할 계산 코드를 작성
	int boardSize = 10;
// 	int p = Integer.parseInt(request.getParameter("p")) or 1;
	int p;
	try{
		p = Integer.parseInt(request.getParameter("p"));
		if(p <= 0) throw new Exception();//강제예외
	}
	catch(Exception e){
		p = 1;
	}
	
// 	p의 값에 따라 시작 row번호와 종료 row번호를 계산
	int endRow = p * boardSize;
	int startRow = endRow - boardSize + 1;
%>



<%
// 	목록,검색을 위해 필요한 프로그래밍 코드
// 	type : 분류 , key : 검색어
	String type = request.getParameter("type");
	String key = request.getParameter("key");
	boolean isSearch = type != null && key != null;
	
	ReportDao reportDao = new ReportDao();
// 	List<BoardDto> list = 목록 or 검색;
	List<ReportDto> list; 
	if(isSearch){
		list = reportDao.reportSearchList(type, key, startRow, endRow);  
	}
	else{
		list = reportDao.reportPagingList(startRow, endRow); 
	}
%>   

<%
// 	페이지 네비게이터 계산 코드를 작성
	
// 	블록 크기를 설정
	int blockSize = 10;
// 	페이지 번호에 따라 시작블록과 종료블럭을 계산
	int startBlock = (p-1) / blockSize * blockSize + 1;
	int endBlock = startBlock + blockSize - 1;
	
// 	endBlock이 마지막 페이지 번호보다 크면 안된다 = 데이터베이스에서 게시글 수를 구해와야 한다.
// 	int count = 목록개수 or 검색개수;
	int count;
	if(isSearch){
		count = reportDao.getReportSearchCount(type, key); 
	}
	else{
		count = reportDao.getReportCount();
	}
// 	페이지 개수 = (게시글수 + 9) / 10 = (게시글수 + 페이지크기 - 1) / 페이지크기
	int pageSize = (count + boardSize - 1) / boardSize;
	
	if(endBlock > pageSize){
		endBlock = pageSize;
	}
%>

<%
	String auth = (String)session.getAttribute("auth");
	boolean isAdmin = false;
	boolean isOwner = false;

	
	if(auth.equals("관리자")){
		AdminDao adminDao = new AdminDao();
		int admin_no = (int)session.getAttribute("check");
		AdminDto adminDto = adminDao.find(admin_no);
		
		boolean Admin =auth.equals("관리자");
		isAdmin = Admin;
	}
	else{
		
		MemberDao memberDao = new MemberDao();
		int member_no = (int)session.getAttribute("check");
		MemberDto memberDto = memberDao.find(member_no);
		
		ReportDto reportDto = reportDao.ownerFind(memberDto.getMember_nick());
		String report_nick = reportDto.getReport_nick();
		memberDto.setMember_nick(report_nick);
				
		boolean Owner = memberDto.getMember_nick().equals(reportDto.getReport_nick());
		isOwner = Owner;
	
}


%>



<jsp:include page="/template/header.jsp"></jsp:include>

<script>
	$(function(){
		//.write-btn을 누르면 글쓰기 페이지로 이동
		$(".write-btn").click(function(){
			//상대경로
			//location.href = "write.jsp";
			//$(location).attr("href", "write.jsp");
			
			//절대경로
			//location.href = "http://localhost:8888/home/board/write.jsp";
			//location.href = "/home/board/write.jsp";
			//location.href = "<%=request.getContextPath()%>/admin/reportWrite.jsp";
		});
		
		 $("#all").change(function(){
		     var check =  $(this).prop("checked");
		     $("input[name=checked]").prop("checked", check);
		    });
		    
		    
		    
		    $("#removeBtn").click(function(){
		    	if($("input[name=checked]").is(":checked")==0){
		    		alert("체크가 되어있지 않습니다");
		    		return false;
		    		
		    	}
		    	
		    });
	});
</script>


<div class="outbox center" style="width:700px">
	
	<div class="row">
		<h2>신고 게시판</h2>
	</div>
		
	<div class="row">
		<form action="ReportDelete.do" method="post">
		<%if(isAdmin){ %>
			<div  class="row left" >
				<input type="submit" id="removeBtn" onclick="if(!confirm('삭제 하시겠습니까?')){return false;}" value="삭제">
			</div>		
		<%} %>
		
		<table class="table table-horizontal">
			<thead>
				<tr>
				<%if(isAdmin){ %>
					<th style="width:10%">
						<input type="checkbox" id="all" style="margin-left:15px">
						<img alt="아래방향아이콘"src="<%=request.getContextPath()%>/adminImage/arrowDown.png" width="8px">
					</th>
				<%} %>
					<th>번호</th>
					<th width="45%">제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>처리상태</th>
				</tr>
			</thead>
			<tbody>
				
					
				<tr>
				<%for(ReportDto dto : list){ %>
				<%if(isAdmin){ %>
					<td>
						<input type="checkbox" name="checked" value="<%=dto.getReport_no()%>">
					</td>
				<%} %>
				
				<%if(isAdmin || isOwner){ %>	
					<td><%=dto.getReport_no()%></td>
					<td class="left">
						<!-- 글 제목을 누르면 상세 페이지로 이동하도록 번호를 첨부하여 링크 설정 -->	
						
						<%if(dto.getReport_qa() > 0){ %>
							<img alt="확인"src="<%=request.getContextPath()%>/adminImage/accept.png" width="15px">
						<%if(isAdmin || isOwner){ %>
							완료
							<a href="reportDetail.jsp?report_no=<%=dto.getReport_no()%>">				
							[<%=dto.getReport_title()%>]
							</a>
						<%} %>
						<%}else{%>
							<%if(isAdmin || isOwner){ %>
							
							<a href="reportDetail.jsp?report_no=<%=dto.getReport_no()%>">				
							[<%=dto.getReport_title()%>]
							</a>
						<%}else{ %>
							접근이 제한되었습니다
						<%}%>	
						<%}%>
					</td>
					<td><%=dto.getReport_nick()%></td>
					<td><%=dto.getReport_date()%></td>
					<td>
						<%if(dto.getReport_qa() > 0){ %>
							<img alt="확인"src="<%=request.getContextPath()%>/adminImage/accept.png" width="15px">
						<%if(isAdmin || isOwner){ %>
							완료
						
						<%} %>
						<%}else{%>
							<%if(isAdmin || isOwner){ %>
							보류
						
						<%}else{ %>
							보류
						<%}%>	
						<%}%>
					
					</td>
					
				</tr>
				<%} %>	
				<%} %>
			</tbody>
		</table>
	</form>
	</div>
	
	<!--
	<div class="row right">
		
		<button class="write-btn input input-inline" ><img alt="쓰기아이콘" src="<%=request.getContextPath()%>/adminImage/pencil.png" style="width:13px;">글쓰기</button>
	</div>
	  -->
	<!-- 검색창 -->
	<form action="qaList.jsp" method="post">
	<div class="row">
		<select name="type" class="input input-inline select">
			<option value="report_title" <%if(type!=null&&type.equals("report_title")){%>selected<%}%>>제목</option>
			<option value="report_nick" <%if(type!=null&&type.equals("report_nick")){%>selected<%}%>>작성자</option>
		</select>
		<%if(isSearch){ %>
		<input type="text" class="input input-inline" name="key" required value="<%=key%>">
		<%}else{ %>
		<input type="text" class="input input-inline" name="key" required>
		<%} %>
		<input type="submit" class="input input-inline" value="검색">
	</div>
	</form>
	
	<!-- 페이지 네비게이션 -->
	<div class="row">
		<ul class="pagination">
			<%if(isSearch){ %>
				<li><a href="qaList.jsp?p=<%=startBlock-1%>&type=<%=type%>&key=<%=key%>">&lt;</a></li>
			<%}else{ %>
				<li><a href="qaList.jsp?p=<%=startBlock-1%>">&lt;</a></li>
			<%} %>
			
			<%for(int i=startBlock; i<=endBlock; i++){ %>
				<%if(i == p){ %>
				<li class="active">
				<%}else{ %>
				<li>
				<%} %>
				<%if(isSearch){ %>
					<!-- 검색용 링크 -->
					<a href="qaList.jsp?p=<%=i%>&type=<%=type%>&key=<%=key%>"><%=i%></a>
				<%}else{ %>
					<!-- 목록용 링크 -->
					<a href="qaList.jsp?p=<%=i%>"><%=i%></a>
				<%} %>
				</li>
			<%} %>
			
			<%if(isSearch){ %>
				<li><a href="qaList.jsp?p=<%=endBlock+1%>&type=<%=type%>&key=<%=key%>">&gt;</a></li>
			<%}else{ %>
				<li><a href="qaList.jsp?p=<%=endBlock+1%>">&gt;</a></li>
			<%} %>
		</ul>
	</div>
	
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>