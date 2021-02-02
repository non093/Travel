<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<jsp:include page="/template/header.jsp"></jsp:include>
<jsp:include page="/template/side.jsp"></jsp:include>
<!--네이버 스마트 에디터 코드 -->
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
	$(function () {
		$(".button-writecancel").click(function(e){
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
.button {
	margin-right: 20px;
}
.button-location {
	margin-top: 10px;
	
}
.findbt {
	position:relative;
	}
	textarea.#ir1 {
		min-height: 380px;
	}
</style>

<body>
	<div style="width: 700px;" class="outbox">
		<h2>글쓰기</h2>
		
		<form id="frm" action="write.do" method="POST">
		<div class="right">
				<input type="button" id="save" class="button button-location" value="등록">
                <input class="button button-writecancel" type="button" value="취소">
			</div>
			<div>
				<select name="board_cate" id="cate" class="input cate-width">
					<option value="말머리를 선택해주세요">말머리를 선택해주세요</option>				
					<option value="공지">공지</option>
					<option value="자유">자유</option>
					<option value="여행">여행</option>
				</select> 
				<select name="board_head" id="head" class="input head-width">
				<option value="">말머리를 선택해주세요</option>
				</select>
			</div>
			<div>
				<input type="text" class="input title-width" name="board_title"
					placeholder="제목을 입력하세요" required>
			</div>
			<div>
				<input type="button" value="위치" class="findbt" onclick="findAddress();">
				<input type="hidden" name="board_address" class="input" onblur="kakaomap();">
				<textarea name="board_content" id="ir1" name="content" style="width:650px; height:350px; " required></textarea>
			</div>
			<div>
				<div id="map" style="width:400px; height:400px;"></div>
			</div>
			
		</form>

	</div>
</body>

<jsp:include page="/template/footer.jsp"></jsp:include>