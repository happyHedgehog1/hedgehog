document.addEventListener("DOMContentLoaded", function () {

    function updateTotal() {
        var totalSum = 0;
        var deliveryCharge = 0;

        // 각 주문 테이블 행을 순회하며 각 항목 계산
        document.querySelectorAll('#orderTableBody tr').forEach(function (row) {

            var priceText = row.querySelector('td:nth-child(3)').textContent.replace('원', '').trim();
            var hdAmountText = row.querySelector('td:nth-child(5) span').textContent.trim();
            var itemDeliveryChargeText = row.querySelector('td:nth-child(6) span').textContent.replace('원', '').trim();

            // 숫자로 변환 가능한 경우에만 이렇게 값을 설정한다.
            var price = isNaN(parseFloat(priceText)) ? 0 : parseFloat(priceText);
            var hdAmount = isNaN(parseFloat(hdAmountText)) ? 0 : parseFloat(hdAmountText);
            var itemDeliveryCharge = isNaN(parseFloat(itemDeliveryChargeText)) ? 0 : parseFloat(itemDeliveryChargeText);


            totalSum += price * hdAmount;
            deliveryCharge += itemDeliveryCharge;

        });

        // 합계 업데이트
        document.getElementById('totalPrice').textContent = (totalSum + '').replace(/,/g, '') + '원';

        document.getElementById('deliveryCharge').textContent = deliveryCharge.toLocaleString() + '원';



        // 결제 예정 금액 업데이트
        var totalOrder = Math.max(0, totalSum + deliveryCharge);
        document.getElementById('totalOrder').textContent = totalOrder + '원';

    }

    // 페이지 로드시 초기 합계 계산 및 업데이트 이건 적립금을 초기화 하기 위해서 저장해둔다
    updateTotal();
    originalUserTotalPoints = document.getElementById("userTotalPoints").innerText;
    originalTotalOrder = document.getElementById("totalOrder").innerText;




});
function updatePointsOnScreen() {
    // 입력된 적립금 값 가져오기
    var enteredPoints = parseInt(document.getElementById("pointInput").value);

    // 현재 총 주문 금액 가져오기
    var totalOrderAmount = parseInt(document.getElementById("totalOrder").innerText);

    // 현재 보유 적립금 가져오기
    var userTotalPoints = parseInt(document.getElementById("userTotalPoints").innerText);

    // 입력된 적립금이 음수이거나 보유 적립금보다 많은지 확인
    if (enteredPoints < 0 || enteredPoints > userTotalPoints) {
        // 사용자에게 메시지 표시: "적립금을 올바르게 입력해주세요."
        alert("적립금을 올바르게 입력해주세요.");
        // 입력 필드 초기화 또는 기타 조치 수행
        document.getElementById("pointInput").value = "";
    } else {
        // 결제 예정 금액에서 입력된 적립금 차감
        var newTotalOrderAmount = Math.max(0, totalOrderAmount - enteredPoints);

        // 보유 적립금에서 입력된 적립금 차감
        var newUserTotalPoints = Math.max(0, userTotalPoints - enteredPoints);

        // 결과를 화면에 업데이트
        document.getElementById("totalOrder").innerText = newTotalOrderAmount + "원";
        document.getElementById("userTotalPoints").innerText = newUserTotalPoints + "원";
    }
}

function resetPointsOnScreen(){

    // 입력된 적립금 입력 필드 초기화
    document.getElementById("pointInput").value = "";

    document.getElementById("totalOrder").innerText = originalTotalOrder;
    document.getElementById("userTotalPoints").innerText = originalUserTotalPoints;

    // 사용자에게 초기화되었음을 알리는 메시지 표시 (예: alert)
    alert("적립금이 초기화되었습니다.");


}


//적립금 적용 버튼 이건 결제했을때 업데이트되야됨 아직 실행안됨
//
// function applyPoints() {
//     // 사용자가 입력한 적립금
//     var enteredPoints = parseInt(document.getElementById('pointInput').value);
//
//     // 현재 총 주문 금액
//     var totalOrderAmount = parseInt(document.getElementById('totalOrder').textContent.replace('원', '').trim());
//
//     // AJAX를 사용하여 서버에 데이터 전송
//     fetch('/clientOrder/cartOrder', {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify({
//             enteredPoints: enteredPoints,
//             totalOrderAmount: totalOrderAmount,
//         }),
//     })
//         .then(response => response.text())
//         .then(data => {
//             // 서버 응답을 처리하는 로직
//
//             // 응답 데이터로 화면 업데이트
//             document.getElementById('totalOrder').textContent = data.updatedTotal.toLocaleString() + '원';
//             document.getElementById('userTotalPoints').textContent = data.updatedUserPoints.toLocaleString() + '원';
//         })
//         .catch(error => {
//             // 오류 처리 로직
//             console.error(error);
//         });
// }

//결제하기버튼을 누르면 실행될 로직 첫번째로 적립금을 업데이트
document.addEventListener("DOMContentLoaded", function () {
    var paymentButton = document.getElementById("paymentButton");

    paymentButton.addEventListener("click", function () {
        // 여기에 결제하기 버튼이 클릭되었을 때 실행될 코드를 추가합니다.
        // 예를 들어, 적립금 업데이트 요청을 서버에 보내고, 결제 모듈 호출 등을 수행할 수 있습니다
    });
});

























//베송 관련 api

function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

