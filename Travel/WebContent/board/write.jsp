<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
	<!--네이버 스마트 에디터 코드 -->
	
<script type="text/javascript" src="<%=ctx %>/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<!-- jQuery를 사용하기위해 jQuery라이브러리 추가 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.0.min.js"></script>
	
	<script type="text/javascript">
var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "/Travel/SE2/SmartEditor2Skin.html",  
          htParams : {
              // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseToolbar : true,             
              // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
              bUseVerticalResizer : true,     
              // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
              bUseModeChanger : true,         
              fOnBeforeUnload : function(){
                   
              }
          }, 
          fOnAppLoad : function(){
              //기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용
              oEditors.getById["ir1"].exec("PASTE_HTML", ["기존 DB에 저장된 내용을 에디터에 적용할 문구"]);
          },
          fCreator: "createSEditor2"
      });
      
      //저장버튼 클릭시 form 전송
      $("#save").click(function(){
          oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
          $("#frm").submit();
      });    
});
 
 
 
</script>
		
		<script>
		//대분류 소분류 분류하는 코드
        const cateList = {
            "공지": ["공지"],
            "자유": ["사담", "질문"],
            "여행": ["강원도", "서울", "인천", "경기도", "충남",
                "충북", "경북", "경남", "대구", "대전", "세종",
                "울산", "부산", "전북", "전남", "광주"],
        }

        document.addEventListener('DOMContentLoaded', () => {
            document.querySelector('#cate').addEventListener('change', e => {
                if (cateList[e.currentTarget.value] !== undefined) {
                    let headSelect = document.querySelector('#head')
                    let headValList = cateList[e.currentTarget.value]
                    let html = ""

                    headValList.forEach(item => {
                        html = html + "<option>" + item + "</option>"
                    })

                    headSelect.innerHTML = html
                }
            })
        }) 
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
</style>
</head>
<body>
	<div style="width: 700px;" class="outbox">
		<h2>글쓰기</h2>
		<form id="frm" action="write.do" method="POST">
			<div>
				<select name="board_cate" id="cate" class="input cate-width">
					<option value="공지">공지</option>
					<option value="자유">자유</option>
					<option value="여행">여행</option>
				</select> <select name="board_head" id="head" class="input head-width">
				</select>
			</div>
			<div>
				<input type="text" class="input title-width" name="board_title"
					placeholder="제목을 입력하세요" required>
			</div>
			<div>
				<textarea name="board_content" rows="10" cols="30" id="ir1" name="content" style="width:650px; height:350px; " required></textarea>
			</div>

			<div class="right">
				<input type="button" id="save" class="button-location" value="등록">
                <input type="button" value="취소">
			</div>
		</form>

	</div>
</body>
</html>