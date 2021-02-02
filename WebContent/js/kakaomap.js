/**
 * kakaomap code + daum 주소 code
 * write.jsp에 적용
 */
function findAddress() {
            new daum.Postcode({
                oncomplete: function (data) {
                    var roadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 참고 항목 변수
                    if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                        extraRoadAddr += data.bname;
                    }
                    if (data.buildingName !== '' && data.apartment === 'Y') {
                        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if (extraRoadAddr !== '') {
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }
                    
                    //roadAddr이 없으면 data.jibunAddress를 넣으세요(예비데이터)
                    document.querySelector("input[name=board_address]").value = roadAddr || data.jibunAddress;

                    var evt = new Event("blur");
                    document.querySelector("input[name=board_address]").dispatchEvent(evt);
                }
            }).open();
        }

        function kakaomap() {
            var mapContainer = document.getElementById('map'),
                mapOption = {
                    center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                    level: 3
                };

            var map = new kakao.maps.Map(mapContainer, mapOption);

            //입력값 가져오기
            var address = document.querySelector("input[name=board_address]").value;
            if (!address) {
                return;
            }

            var geocoder = new kakao.maps.services.Geocoder();
			
            //address를 db에서 받아온다면??
            geocoder.addressSearch(address, function (result, status) {
                if (status === kakao.maps.services.Status.OK) {
                    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                    var marker = new kakao.maps.Marker({
                        map: map,
                        position: coords
                    });

                    var infowindow = new kakao.maps.InfoWindow({
                        content: '<div style="width:250px;text-align:center;padding:6px 0;">' + address + '</div>'
                    });
                    infowindow.open(map, marker);

                    map.setCenter(coords);
                }
            });
        }