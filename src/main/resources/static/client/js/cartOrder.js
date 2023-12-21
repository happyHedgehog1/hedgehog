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
        document.getElementById('productTotalPrice').textContent = (totalSum + '').replace(/,/g, '') + '원';

        document.getElementById('deliveryCharge').textContent = deliveryCharge + '원';



        // 결제 예정 금액 업데이트
        var totalOrder = Math.max(0, totalSum + deliveryCharge);
        document.getElementById('totalOrder').textContent = totalOrder + '원';

        // 값을 넘겨주기 위해서 생성
        var totalOrderValue = document.getElementById('totalOrder').textContent.trim();

    }

    // 페이지 로드시 초기 합계 계산 및 업데이트 이건 적립금을 초기화 하기 위해서 저장해둔다
    updateTotal();
    originalUserTotalPoints = document.getElementById("userTotalPoints").innerText;
    originalTotalOrder = document.getElementById("totalOrder").innerText;

});


//     document.addEventListener("DOMContentLoaded", function () {
//     // ... (기존 코드)
//
//     // "적립금 적용" 버튼에 대한 참조를 얻어옴
//     var applyPointsButton = document.getElementById("applyPointsButton");
//
//     // "적립금 적용" 버튼 클릭 이벤트 리스너 등록
//     applyPointsButton.addEventListener("click", function () {
//     // 적립금 적용 함수 호출
//     updatePointsOnScreen();
//
//     // 적립금 적용 버튼 비활성화
//     applyPointsButton.disabled = true;
// });
//
//     // "적립금 초기화" 버튼 클릭 이벤트 리스너 등록
//     document.getElementById("resetPointsButton").addEventListener("click", function () {
//     // 적립금 초기화 함수 호출
//     resetPointsOnScreen();
//
//     // 적립금 적용 버튼 활성화
//     applyPointsButton.disabled = false;
// });
//
// });




var newTotalOrderAmount = 0;
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
        newTotalOrderAmount = Math.max(0, totalOrderAmount - enteredPoints);

        // 보유 적립금에서 입력된 적립금 차감
        var newUserTotalPoints = Math.max(0, userTotalPoints - enteredPoints);

        // 결과를 화면에 업데이트
        // updatePointsOnScreen();
        document.getElementById("totalOrder").innerText = newTotalOrderAmount + "원";
        document.getElementById("userTotalPoints").innerText = newUserTotalPoints + "원";
        console.log("New Total Order Amount: " + newTotalOrderAmount + "원");
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


//결제하기버튼을 누르면 실행될 로직 첫번째로 적립금을 업데이트
document.addEventListener("DOMContentLoaded", function () {
    var paymentButton = document.getElementById("paymentButton");

    paymentButton.addEventListener("click", function () {
        // 여기에 결제하기 버튼이 클릭되었을 때 실행될 코드를 추가합니다.
        // 예를 들어, 적립금 업데이트 요청을 서버에 보내고, 결제 모듈 호출 등을 수행할 수 있습니다
    });
});


function sendValuesToServer() {
    var totalOrder = document.getElementById('totalOrder').innerText;
    var userTotalPoints = document.getElementById('userTotalPoints').innerText;
    var deliveryCharge = document.getElementById('deliveryCharge').innerText;

    // AJAX로 서버에 값을 전송
    $.ajax({
        type: 'POST',
        url: '/your-server-endpoint',  // 서버의 엔드포인트를 적절히 변경
        data: {
            totalOrder: totalOrder,
            userTotalPoints: userTotalPoints,
            deliveryCharge: deliveryCharge
        },
        success: function(response) {
            // 서버 응답에 대한 처리
            console.log(response);
        },
        error: function(error) {
            console.error(error);
        }
    });
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


//카카오페이 관련 결제하기 버튼을 누르면 동작

const adminKey = 'c84521fef561a0b8f63c5438a75390a6';
const formData = new FormData();



$(function(){
    $("#btn-kakao-pay").click(function(){

        // 필수입력값을 확인.
        var name = $("input[name='pay-name']").val();
        var phone = $("input[name='pay-phone']").val();
        var email = $("input[name='pay-email']").val();
        var deliveryName = $("input[name='pay-name2']").val();
        var deliveryPhone = $("input[name='pay-phone2']").val();
        var deliveryRequest = $("input[name='deliveryRequest']").val();
        // var productName = $("input[name='productName']").val();

        if(name == ""){
            $("#name-input input[name='pay-name']").focus()
        }
        if(phone == ""){
            $("#phone1-input input[name='pay-phone']").focus()
        }
        if(email == ""){
            $("#email-input input[name='pay-email']").focus()
        }
        if(deliveryName == ""){
            $("#name-input2 input[name='pay-name2']").focus()
        }
        if(deliveryPhone == ""){
            $("#phone2-input input[name='pay-phone2']").focus()
        }
        if(deliveryRequest == ""){
            $("#form-payment input[name='deliveryRequest']").focus()
        }

        // 결제 정보를 form에 저장한다.
        // let totalPayPrice = parseInt($("#total-pay-price").val())
        // let totalPrice = parseInt($("#total-price").val())
        // let discountPrice = totalPrice - totalPayPrice

        let AllOriginalTotalOrder = parseInt(document.getElementById("totalOrder").textContent.replace('원', '').trim());
        //배송예정금액
        let originalTotalOrder = parseInt(document.getElementById("productTotalPrice").textContent.replace('원', '').trim());
        //이건 text를 통해서 가져와야됨
        //상품합계금액
        let deliveryPrice = parseInt(document.getElementById("deliveryCharge").textContent.replace('원', '').trim());
        //배송비
        let savedPoint = parseInt(document.getElementById("userTotalPoints").textContent.replace('원', '').trim());
        //적립금을 사용학고 나서의 보유적립금
        let usingPoint = originalTotalOrder + deliveryPrice - AllOriginalTotalOrder;
        //적용을 누른 적립금

        //==============================

        ///////////////
        // var inputName = $("#name-input").val(); // Input에 적은 이름
        // var productTotal = $("#totalPrice").val(); // 상품 합계금액
        // var deliveryPrice = $("#deliveryCharge").val();//배송비
        // var totalOrder = $("#totalOrder").text(); //최종결제 금액
        // // var discountPrice = (totalOrder - (productTotal + deliveryPrice)); //사용한 적립금
        // var inputPhone = $("#phone2-input").val(); //전화번호
        // var deliveryRequest = $("#deliveryRequest").val(); //배송요청사항
        //////

        // console.log(originalTotalOrder)

        //setter로 받아서 파라미터를 넘겨주기

        $.ajax({
            type:'post'
            ,url:'/order/pay'
            ,data:{
                name:name
                ,phone:phone
                ,email:email
                //==============================
                ,savedPoint:savedPoint
                ,originalTotalOrder:originalTotalOrder
                ,deliveryPrice:deliveryPrice
                ,AllOriginalTotalOrder:AllOriginalTotalOrder
                ,usingPoint:usingPoint
                ,deliveryName:deliveryName
                ,deliveryPhone:deliveryPhone
                ,deliveryRequest:deliveryRequest
                // ,productName:productName


            },
            success:function(response){
                location.href = response.next_redirect_pc_url
            }
        })
    })
})




























// const apiUrl = 'http://localhost:8080'
//
//
// // 비동기 함수를 사용하여 서버에서 데이터 가져오기
// async function fetchCartData() {
//     try {
//         const response = await fetch(apiUrl);
//
//         if (!response.ok) {
//             throw new Error(`HTTP error! Status: ${response.status}`);
//         }
//
//         const data = await response.json();
//
//         // 가져온 데이터를 활용하여 페이지 업데이트
//         updatePageWithData(data);
//     } catch (error) {
//         console.error('Error fetching data:', error.message);
//     }
// }
//
// // 페이지 업데이트 함수
// function updatePageWithData(data) {
//     // 화면에 표시하고 hidden input 필드에 값을 설정
//     document.getElementById("cart-info").innerHTML = `
//         <p>상품명: ${data["pay-name"]}</p>
//         <p>전화번호: ${data["pay-phone"]}</p>
//         <p>이메일: ${data["pay-email"]}</p>
//         <p>총 결제 금액: ${data["total-pay-price"]}</p>
//         <p>상품 가격 합계: ${data["total-price"]}</p>
//         <p>포인트 사용: ${data["point-use"]}</p>
//     `;
//
//     // hidden input 필드에 값을 설정
//     document.getElementById("pay-name").value = data["pay-name"];
//     document.getElementById("pay-phone").value = data["pay-phone"];
//     document.getElementById("pay-email").value = data["pay-email"];
//     document.getElementById("total-pay-price").value = data["total-pay-price"];
//     document.getElementById("total-price").value = data["total-price"];
//     document.getElementById("point-use").value = data["point-use"];
// }
//
// // 페이지 로드 시 서버에서 데이터 가져오기
// window.onload = fetchCartData;
//
//
//
//











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

