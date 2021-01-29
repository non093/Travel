<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="travel.beans.*" %>
<%@ page import="java.util.*" %>

<jsp:include page="/template/header.jsp"></jsp:include>

<%

	request.setCharacterEncoding("UTF-8");
	String type = request.getParameter("type");
	String key = request.getParameter("key");
	
	boolean isSearch = type != null && key != null;
	

	AdminDao adminDao = new AdminDao();
	List<MemberDto> list = adminDao.memberSelect(type, key);
	
// 	list의 상태가 총 3가지 발생할 수 있다.
// 	(1) list == null
//	(2) list.isEmpty() 또는 list.size() == 0
//	(3) !list.isEmpty() 또는 list.size() > 0
%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
// 	페이지 분할 계산 코드를 작성
	int memberSize = 10;
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
	int endRow = p * memberSize;
	int startRow = endRow - memberSize + 1;
%>



<%
// 	목록,검색을 위해 필요한 프로그래밍 코드
// 	type : 분류 , key : 검색어
	
	
	
// 	List<BoardDto> list = 목록 or 검색;
	
//	List<BoardDto> list = 목록 or 검색;
	


	if(isSearch){
		list = adminDao.memberSearchList(type, key, startRow, endRow);  
	}
	else{
		list = adminDao.memberPagingList(startRow, endRow); 
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
		count = adminDao.getMemberSearchCount(type, key); 
	}
	else{
		count = adminDao.getMemberCount();
	}
// 	페이지 개수 = (게시글수 + 9) / 10 = (게시글수 + 페이지크기 - 1) / 페이지크기
	int pageSize = (count + memberSize - 1) / memberSize;
	
	if(endBlock > pageSize){
		endBlock = pageSize;
	}
%>




<script>
	//.delete-link를 누르면 확인창 후 진행하도록 구현
	$(function(){
		$(".delete-link").click(function(e){
			e.preventDefault();//a, form태그는 기본 설정된 이벤트를 방지해야 합니다
			
			var choice = window.confirm("정말 회원탈퇴 진행하시겠습니까?");
			if(choice){
				//location.href = $(this).attr("href");
				$(location).attr("href", $(this).attr("href"));
			}
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

<div class="outbox" style="width:800px">
	<!-- 제목 -->
	<div class="row center">
		<h2>회원 검색</h2>
	</div>
	
	
	
	<!-- 결과 화면 : 검색결과가 있는 경우와 없는 경우로 구분하여 출력 -->
	
	<%if(list == null){ %>
	<!-- 처음 들어온 경우 보여줄 화면 -->
	<div class="row center">
		<h4>검색분류와 검색어를 입력해주세요</h4>
	</div>
	<%} else if (list.isEmpty()){ %>
	<!-- 검색 결과가 없는 경우의 화면 -->
	<div class="row center">
		<h4>검색 결과가 존재하지 않습니다</h4>
	</div>
	<%} else { %>
	<!-- 검색 결과가 있는 경우의 화면 -->
	<div class="row">
		
		<form action="adminMemberSelectDelete.do" method="post">
		
		<table class="table table-horizontal">
			<thead>
				<tr>
					<th style="width:10%">
							<input type="checkbox" id="all" style="margin-left:15px;" >
							<img alt="아래방향아이콘"src="<%=request.getContextPath()%>/adminImage/arrowDown.png" width="8px">
					</th>
					<th>회원번호</th>
					<th>아이디</th>
					<th>닉네임</th>
					<th>이메일</th>
					<th>등급</th>
					<th>관리메뉴</th>
				</tr>
			</thead>
			<tbody>
				<!-- 데이터 개수만큼 줄을 반복하며 출력 -->
				<%for(MemberDto memberDto : list){ %>
				<tr>
					<td>
						<input type="checkbox" name="checked" value="<%=memberDto.getMember_no()%>">
					</td>
					<td><%=memberDto.getMember_no()%></td>
					<td><%=memberDto.getMember_id()%></td>
					<td><%=memberDto.getMember_nick()%></td>
					<td><%=memberDto.getMember_email()%></td>
					<td><%=memberDto.getMember_auth()%></td>
					<td>
						<a href="adminMemberProfile.jsp?member_no=<%=memberDto.getMember_no()%>">상세</a>
						<a href="adminEditMember.jsp?member_no=<%=memberDto.getMember_no()%>">수정</a>
						<a class="delete-link" href="adminDelete.do?member_no=<%=memberDto.getMember_no()%>">탈퇴</a>
					</td>
				</tr>
				<%} %>
			</tbody>
		</table>
		<input type="submit" id="removeBtn" style="float:right;" onclick="if(!confirm('삭제 하시겠습니까?')){return false;}" value="삭제">
		</form>
	</div>
	<%} %>
		<!-- 검색 폼 -->
	<form action="adminMemberList.jsp" method="get">
		<div class="row center search">
			<select name="type" class="input input-inline select" required>
				<option value="">분류 선택</option>
				<option value="member_no" <%if(type!=null&&type.equals("member_no")){%>selected<%}%>>번호</option>
				<option value="member_id" <%if(type!=null&&type.equals("member_id")){%>selected<%}%>>아이디</option>
				<option value="member_nick" <%if(type!=null&&type.equals("member_nick")){%>selected<%}%>>닉네임</option>
				<option value="member_email" <%if(type!=null&&type.equals("member_email")){%>selected<%}%>>이메일</option>
				<option value="member_auth" <%if(type!=null&&type.equals("member_auth")){%>selected<%}%>>등급</option>
			</select>
			
			<%if(isSearch){ %>
			<input type="text" name="key" placeholder="검색어 입력" required class="input input-inline" value="<%=key%>">
			<%}else{ %>
			<input type="text" name="key" placeholder="검색어 입력" required class="input input-inline">
			<%} %>
			
			<input type="submit" value="검색" class="input input-inline">
		</div>
	</form>
		
		
		<div class="row center">
		<ul class="pagination">
			<%if(isSearch){ %>
				<li><a href="adminMemberList.jsp?p=<%=startBlock-1%>&type=<%=type%>&key=<%=key%>">&lt;</a></li>
			<%}else{ %>
				<li><a href="adminMemberList.jsp?p=<%=startBlock-1%>">&lt;</a></li>
			<%} %>
			
			<%for(int i=startBlock; i<=endBlock; i++){ %>
				<%if(i == p){ %>
				<li class="active">
				<%}else{ %>
				<li>
				<%} %>
				<%if(isSearch){ %>
					<!-- 검색용 링크 -->
					<a href="adminMemberList.jsp?p=<%=i%>&type=<%=type%>&key=<%=key%>"><%=i%></a>
				<%}else{ %>
					<!-- 목록용 링크 -->
					<a href="adminMemberList.jsp?p=<%=i%>"><%=i%></a>
				<%} %>
				</li>
			<%} %>
			
			<%if(isSearch){ %>
				<li><a href="adminMemberList.jsp?p=<%=endBlock+1%>&type=<%=type%>&key=<%=key%>">&gt;</a></li>
			<%}else{ %>
				<li><a href="adminMemberList.jsp?p=<%=endBlock+1%>">&gt;</a></li>
			<%} %>
		</ul>
	</div>
	
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>