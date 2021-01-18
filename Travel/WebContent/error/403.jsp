<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	이 페이지는 404번 상태일 때 보여져야할 화면이며, 독자적인 실행은 필요가 없다
	= 오류 상태에 따라 실행 페이지를 연결하려면 "설정"이 필요하며, "설정파일"을 알아야 한다.
	= 운영관련 설정은 "web.xml"이라는 파일에서 진행하며 서버 설정에 존재하며 원한다면 프로젝트 내부에도 생성할 수 있다.
 -->

<jsp:include page="/template/header.jsp"></jsp:include>

<div class="outbox center" style="width:500px">
	<div class="row">
		<h2>이용 권한이 없습니다</h2>
	</div>
	<div class="row">
		<img alt="오류이미지" src="http://placeimg.com/500/500/tech" width="100%" height="350">
	</div>
</div>

<jsp:include page="/template/footer.jsp"></jsp:include>