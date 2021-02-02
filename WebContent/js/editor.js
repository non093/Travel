/**
 * 네이버 스마트 에디터 (2.3.1) javascript code + 대분류, 소분류 코드
 */

var oEditors = [];
$(function(){
      nhn.husky.EZCreator.createInIFrame({
          oAppRef: oEditors,
          elPlaceHolder: "ir1", //textarea에서 지정한 id와 일치해야 합니다. 
          //SmartEditor2Skin.html 파일이 존재하는 경로
          sSkinURI: "../SE2/SmartEditor2Skin.html",    
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
            
            cateList.여행.forEach(item => {
                
                var optionEl = document.createElement('option')
                
                optionEl.value = item
                optionEl.innerText = item          

                console.log(document.querySelectorAll('select')[1].appendChild(optionEl))
               

            })


        })

