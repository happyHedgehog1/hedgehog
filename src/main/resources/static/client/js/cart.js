// document.addEventListener("DOMContentLoaded", function () {
//     // 감소 버튼에 대한 이벤트 리스너 등록
//     var decreaseButtons = document.querySelectorAll('.decrease-button');
//
//     decreaseButtons.forEach(function (button) {
//
//         button.addEventListener('click', function () {
//             var nextElement = this.nextElementSibling;
//             if (nextElement){
//                 console.log(nextElement.innerText);
//             }else {
//                 console.log("최쇠 수량은 1개 입니다.")
//             }
//             // var productCode = button.getAttribute('data-product-code');
//             // updateQuantity(-1, productCode);
//             console.log(this)
//         });
//     });
//
//     // 증가 버튼에 대한 이벤트 리스너 등록
//     var increaseButtons = document.querySelectorAll('.increase-button');
//     increaseButtons.forEach(function (button) {
//         // button.addEventListener('click', function () {
//         //     var productCode = button.getAttribute('data-product-code');
//         //     updateQuantity(1, productCode);
//         // });
//     });
// });

document.addEventListener("DOMContentLoaded", function () {
    // 감소 버튼에 대한 이벤트 리스너 등록
    var decreaseButtons = document.querySelectorAll('.decrease-button');
    decreaseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 현재 수량 요소 가져오기
            var quantityElement = this.nextElementSibling;

            // 현재 수량 가져오기
            var currentQuantity = parseInt(quantityElement.innerText);

            // 새로운 수량 계산
            var newQuantity = Math.max(currentQuantity - 1, 1);

            // 수량 표시 업데이트
            quantityElement.innerText = newQuantity;
        });
    });

    // 증가 버튼에 대한 이벤트 리스너 등록
    var increaseButtons = document.querySelectorAll('.increase-button');
    increaseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 현재 수량 요소 가져오기
            var quantityElement = this.previousElementSibling;

            // 현재 수량 가져오기
            var currentQuantity = parseInt(quantityElement.innerText);

            // 새로운 수량 계산
            var newQuantity = currentQuantity + 1;

            // 수량 표시 업데이트
            quantityElement.innerText = newQuantity;
        });
    });
});


function updateQuantity(change, productCode) {

    console.log("Change: " + change + ", Product Code: " + productCode);

    // 해당 상품의 수량 요소를 가져오기
    var quantityElement = document.getElementById("quantity-display-" + productCode);

    // 현재 수량 가져오기
    var currentQuantity = parseInt(quantityElement.innerText);

    // 새로운 수량 계산
    var newQuantity = change + currentQuantity;

    // 최소 수량을 1로 설정
    newQuantity = Math.max(newQuantity, 1);

    // 수량 표시 업데이트
    quantityElement.innerText = newQuantity;

    // 콘솔에 로그 출력
    console.log("Product Code: " + productCode + ", New Quantity: " + newQuantity);
}






function handleFormSubmit() {
    // 폼 제출 전에 수행할 작업 추가 (필요한 경우)
    return true; // true를 반환하면 폼이 제출됩니다.
}


document.addEventListener("DOMContentLoaded", function () {
    // th에 있는 체크박스를 선택
    const headerCheckbox = document.querySelector('th input.cartcheckbox');

    // 모든 td에 있는 체크박스를 선택
    const cellCheckboxes = document.querySelectorAll('td input.cartcheckbox');

    console.log('headerCheckbox:', headerCheckbox);
    console.log('cellCheckboxes:', cellCheckboxes);

    // th 체크박스에 이벤트 리스너 추가
    headerCheckbox.addEventListener('change', function () {
        // th 체크박스의 상태에 따라 모든 td 체크박스의 상태 변경
        cellCheckboxes.forEach(function (checkbox) {
            checkbox.checked = headerCheckbox.checked;
        });
    });
});

function selectAllItems() {
    // 모든 cartcheckbox 요소들을 가져옴
    var checkboxes = document.querySelectorAll('.cartcheckbox');

    // 전체상품주문하기 버튼이 클릭되었을 때 각 체크박스의 상태를 변경
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = true;
    });
}


//선택상품주문할때 체크 안되어있으면 alert창 띄우기

$(document).ready(function() {
    $('.select_order_btn').click(function(event) {


        // 체크박스가 하나라도 체크되어 있는지 확인
        var isChecked = $('.cartcheckbox:checked').length > 0;

        if (!isChecked) {
            // 체크박스가 하나도 체크되어 있지 않으면 알림창 띄우기
            alert('상품을 선택해주세요.');
            // 이벤트의 기본 동작을 중지
            event.preventDefault();
        }
    });
});

// function submitForm() {
//     // 선택된 체크박스들을 저장할 배열
//     var selectedItems = [];
//
//     // 모든 체크박스에 대해 반복
//     var checkboxes = document.getElementsByName('cartcheckbox');
//     for (var i = 0; i < checkboxes.length; i++) {
//         if (checkboxes[i].checked) {
//             // 체크된 체크박스의 값을 배열에 추가
//             selectedItems.push(checkboxes[i].value);
//         }
//     }
//
//     // 폼에 추가 데이터를 넣거나 수정할 수 있으면 여기서 처리
//
//     // 폼 전송
//     document.getElementById('cart_order_form').submit();
//
//
// }


// 체크박스에서 체크된 것만 주문성작성 페이지로 넘기기 위한 js
// document.addEventListener('DOMContentLoaded', function () {
//     // 주문서 작성 폼 요소를 가져옵니다.
//     const orderForm = document.querySelector('.cart_order_form');
//
//     // 폼이 제출될 때의 이벤트를 처리합니다.
//     orderForm.addEventListener('submit', function (event) {
//         // 선택된 상품 정보를 담을 배열을 생성합니다.
//         const selectedItems = [];
//
//
//         // 체크박스 요소들을 가져옵니다.
//         const checkboxes = document.querySelectorAll('.cart_table input[type="checkbox"]:checked');
//
//         // 각 체크된 상품에 대해 정보를 추출합니다.
//         checkboxes.forEach(function (checkbox) {
//             const row = checkbox.closest('tr');
//             const itemName = row.querySelector('td:nth-child(2)').textContent.trim();
//             const itemQuantity = row.querySelector('td:nth-child(5)').textContent.trim();
//
//             // 정보를 객체로 저장하고 배열에 추가합니다.
//             const itemInfo = {
//                 name: itemName,
//                 quantity: itemQuantity,
//             };
//             selectedItems.push(itemInfo);
//
//         });
//
//         // // 추출한 정보를 문자열로 변환하여 폼 데이터로 추가합니다.
//         // const hiddenInput = document.createElement('input');
//         // hiddenInput.type = 'hidden';
//         // hiddenInput.name = 'selectedItems';
//         // hiddenInput.value = JSON.stringify(selectedItems);
//         // orderForm.appendChild(hiddenInput);
//
//     });
// });