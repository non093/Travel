<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.*" %>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<%
	int board_no = Integer.parseInt(request.getParameter("board_no"));

	BoardDao boardDao = new BoardDao();
	BoardDto boardDto = boardDao.find(board_no);
	
	boolean map = boardDto.getBoard_address() != null;

%>
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/side.jsp"></jsp:include>
<!--네이버 스마트 에디터 코드 -->
<script>
var root = "<%=request.getContextPath()%>";
</script>

<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
<!-- editor.js(editor + 분류) -->
<script type="text/javascript" src="<%=ctx %>/js/editor.js"> </script>
<!-- kakaomap + 다음 api + 카카오 API Keycode -->
<script type="text/javascript" src="<%=ctx %>/js/kakaomap.js"></script>	
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9c16001f42b911de2991dc18f6649856&libraries=services"></script>

<script>
	$(function (){
		$("select[name=board_cate]").val("<%=boardDto.getBoard_cate()%>");
	});

	$(function (){
		$("select[name=board_head]").val("<%=boardDto.getBoard_head()%>");
	});
	
	$(function () {
		$(".edit-cancle").click(function () {
			history.back();
		});
	});
	
</script>
<style>
.right {
	float: right;
}

.outbox {
	margin: 0 auto;
	border: 1px solid black;
}

.input {
	margin: 0.5rem 0px;
	padding: 0.4rem;
}

.title-width {
	width: 590px;
}

.cate-width {
	width: 400px;
}

.head-width {
	width: 200px;
}

.button-location {
	margin-top: 10px;
	margin-right: 95px;
}
.findbt {
	position:relative;
}
</style>


	<div style="width: 700px;" class="outbox">
		<h2>글수정</h2>
		
		<form id="frm" action="<%=ctx %>/board/edit.do" method="POST">
		<input type="hidden" name="board_no" value="<%=boardDto.getBoard_no() %>">
			<div>
				<select name="board_cate" id="cate" class="input cate-width">
					<option value="공지">공지</option>
					<option value="자유">자유</option>
					<option value="여행">여행</option>
				</select> 
				<select name="board_head" id="head" class="input head-width" >
					<option value="<%=boardDto.getBoard_head()%>"><%=boardDto.getBoard_head()%></option>
				</select>
			</div>
			<div>
				<input type="text" class="input title-width" name="board_title" value="<%=boardDto.getBoard_title()%>">
			</div>
			<div>
				<input type="button" value="위치" class="findbt" onclick="findAddress();">
				<%  if(map) {%>
				<body onload="kakaomap();">
				<input type="hidden" name="board_address" class="input" value="<%=boardDto.getBoard_address() %>" onblur="kakaomap();">
				</body>
				<% } else { %>
				<input type="hidden" name="board_address" class="input" value="<%=boardDto.getBoard_address() %>" onblur="kakaomap();">
				<% } %>
				<textarea name="board_content" rows="10" cols="30" id="ir1" name="content" style="width:650px; height:350px;" required>
				<%=boardDto.getBoard_content() %>
				</textarea>
			</div>
			<div>
				<div id="map" style="width:600px; height:600px;"></div>
			</div>
			<div class="right">
				
				<input type="submit" id="save" class="button-location" value="수정">
                <input type="button" class="edit-cancle" value="취소">
			</div>
		</form>

	</div>
<jsp:include page="/template/footer.jsp"></jsp:include>