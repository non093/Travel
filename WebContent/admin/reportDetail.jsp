<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>
<%
	/////////////////////////////////////////////////////////////////
	//  게시글 구하는 코드
	/////////////////////////////////////////////////////////////////
	
	//1.번호를 받는다
	int report_no = Integer.parseInt(request.getParameter("report_no"));
	
	//2.조회수를 증가시킨 후 게시글 정보를 구한다
	ReportDao reportDao = new ReportDao();
	ReportDto reportDto = reportDao.find(report_no);
	
	//참고 : 작성자의 다른 정보가 필요할 경우 검색한다.
	MemberDao memberDao = new MemberDao();
	MemberDto writerDto = memberDao.find(reportDto.getReport_nick());
	
	//3.화면에 출력한다
	
	//작성자 본인 또는 관리자인지 파악하기 위한 검사코드
	//- 관리자 : 세션에 auth 항목을 조사하여 관리자인지 확인
	//- 본인 : 로그인한 사용자의 ID와 게시글의 작성자가 같은지 확인
	
	
	
	
	
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
		int member_no = (int)session.getAttribute("check");
		MemberDto memberDto = memberDao.find(member_no);
		boolean Owner = memberDto.getMember_nick().equals(reportDto.getReport_nick());
		isOwner = Owner;
	}
	
	
%>

<%
	/////////////////////////////////////////////////////////////////
	//  댓글 목록 구하는 코드
	//	= 모든 댓글을 보는 경우는 없음 = 게시글별로 보는 경우만 존재 = 작성순으로 정렬
	//	= select * from reply where reply_origin = ? order by reply_no asc
	/////////////////////////////////////////////////////////////////

%>
    
<jsp:include page="/template/header.jsp"></jsp:include>

<script>
	//각종 버튼들을 누르면 해당 위치로 이동하도록 구문을 작성
	$(function(){
		//새글 버튼을 누르면 write.jsp로 보낸다
		$(".write-btn").click(function(){
			//location.href = "write.jsp";//상대경로
			location.href = "<%=request.getContextPath()%>/board/write.jsp";//절대경로
		});
		
		//수정 버튼을 누르면 edit.jsp로 번호를 첨부하여 보낸다
		$(".edit-btn").click(function(){
			location.href = "reportEdit.jsp?report_no=<%=report_no%>";
		});
		
		//삭제 버튼을 누르면 확인창을 띄우고 확인을 누르면 삭제 페이지에 번호를 첨부하여 보낸다
		$(".delete-btn").click(function(){
			if(confirm("정말 지우시겠습니까?")){
				location.href = "reportDelete.do?report_no=<%=report_no%>";
				//location.href = "<%=request.getContextPath()%>/admin/reportDelete.do?report_no=<%=report_no%>";
			}
		});
		
		//목록 버튼을 누르면 list.jsp로 보낸다
		$(".list-btn").click(function(){
			//location.href = "list.jsp";//상대경로
			location.href = "<%=request.getContextPath()%>/admin/reportList.jsp";//절대경로
		});
		
		
	});
</script>

<div class="outbox" style="width:600px;">
	<div class="row center">
		<h2>
			<%=reportDto.getReport_no()%>번 게시글
		</h2>
	</div>
	<div class="row">
		<table class="table table-border">
			<thead>
				<tr>
					<th class="right" colspan="2">
						
						<%if(isAdmin){ %>
						<!-- 수정과 삭제 버튼은 작성자 본인과 관리자에게만 표시되어야 한다 -->
						<button class="input input-inline edit-btn">요청</button>
						<%} %>
						<%if(isOwner || isAdmin){ %>
						<!-- 수정과 삭제 버튼은 작성자 본인과 관리자에게만 표시되어야 한다 -->
						<button class="input input-inline delete-btn">삭제</button>
						<%} %>
						<button class="input input-inline list-btn">목록</button>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>작성자</th>
					<td class="left"><%=reportDto.getReport_nick()%></td>
				</tr>
				<tr>
					<th width="20%">제목</th>
					<td class="left"><%=reportDto.getReport_title()%></td>
				</tr>
				<tr height="200">
					<th>내용</th>
					<td class="left" valign="top"><%=reportDto.getReport_content()%></td>
				</tr>
				<%if(reportDto.getReport_answer() != null){ %>
				<tr height="200">
					<th>답변</th>
					<td class="left" valign="top"><%=reportDto.getReport_answer()%></td>
				</tr>
				<%}%>
				<tr>
					<th>작성일</th>
					<td class="left"><%=reportDto.getReport_date()%></td>
				</tr>
				
				
				
				<!-- 댓글 목록 -->
		
			</tbody>
			<tfoot>
				<tr>
					<th class="right" colspan="2">
						
						<%if(isAdmin){ %>
						<!-- 수정과 삭제 버튼은 작성자 본인과 관리자에게만 표시되어야 한다 -->
						<button class="input input-inline edit-btn">요청</button>
						<%} %>
						<%if(isOwner || isAdmin){ %>
						<!-- 수정과 삭제 버튼은 작성자 본인과 관리자에게만 표시되어야 한다 -->
						<button class="input input-inline delete-btn">삭제</button>
						<%} %>
						<button class="input input-inline list-btn">목록</button>
					</th>
				</tr>
			</tfoot>			
		</table>
	</div>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>