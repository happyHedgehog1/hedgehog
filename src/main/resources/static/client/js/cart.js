

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






// 체크박스 수량 수정 //








function handleFormSubmit() {
    // 폼 제출 전에 수행할 작업 추가 (필요한 경우)
    return true; // true를 반환하면 폼이 제출됩니다.
}


//폼제출 해야됨

//=======================헤더 체크박스 누르면 실행 모든 체크박스가 선택되고 선택된 상품들의 가격이 합계에 나와야한다.

document.addEventListener("DOMContentLoaded", function () {
    // th에 있는 체크박스를 선택
    const headerCheckbox = document.querySelector('input[name="cartcheckbox1"]');

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
        updateTotalPrice();
    });

});

//============================

//======================전체상품 주문하기 버튼 누르면 실행 ==========================
document.addEventListener('DOMContentLoaded', function () {


    // 버튼 요소 찾기
    var selectAllItemsButton = document.getElementById('selectAllItems');

    // 클릭 이벤트 등록
    selectAllItemsButton.addEventListener('click', function () {
        selectAllItems();
    });
});

function selectAllItems() {
    // 모든 cartcheckbox 요소들을 가져옴
    var checkboxes = document.querySelectorAll('[name="cartcheckbox"]');

    // 전체상품주문하기 버튼이 클릭되었을 때 각 체크박스의 상태를 변경
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = true; // 모두 체크하도록 변경
    });

    updateTotalPrice();
    window.location.href = '/clientOrder/cartOrder';
}
//======================

//==============선택상품삭제

// 삭제 버튼에 대한 이벤트 리스너 등록
document.addEventListener('click', function (event) {
    if (event.target.classList.contains('select_like_btn')) {
        // 클릭된 버튼이 삭제 버튼인 경우
        var row = event.target.closest('tr'); // 해당 행 가져오기
        row.remove(); // 행 삭제
        updateTotalPrice(); // 총 가격 업데이트
    }
});

//==============



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


//여기부터

// 페이지 로딩 시에도 한 번 호출
document.addEventListener('DOMContentLoaded', function () {
    updateTotalPrice();

// 수량 조절 버튼 클릭 시에도 호출
document.addEventListener('click', function (event) {
    if (event.target.classList.contains('quantity-button')) {
        updateTotalPrice();
    }

});
    var checkboxes = document.querySelectorAll('.cartcheckbox');
    checkboxes.forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            // 선택된 상품의 가격 업데이트
            updateTotalPrice();


        });
    });



        var headerCheckbox = document.getElementById('headerCheckbox');
        if (headerCheckbox) {
            headerCheckbox.addEventListener('change', function () {



                    // 전체 선택 체크박스의 상태에 따라 합계 업데이트
                    updateTotalPrice();

            });
        }
});

// function updateAllCheckboxes(isChecked) {
//     // 모든 행의 체크박스 상태 변경
//     var checkboxes = document.querySelectorAll('.cart_table tbody tr td:first-child input');
//     checkboxes.forEach(function (checkbox) {
//         checkbox.checked = isChecked;
//     });
// }
//이거 때문에 무한루프였던거 같은데



//여기까지







function updateTotalPrice() {
    // 모든 체크된 체크박스 가져오기
    // var checkedCheckboxes = document.querySelectorAll('.cart_table input.cartcheckbox:checked');
    var checkedCheckboxes = document.querySelectorAll('.cart_table input[name="cartcheckbox"]:checked');

    // 총 상품 구매 금액 및 배송비를 저장할 변수 초기화
    var totalProductAmount = 0;
    var totalDeliveryCharge = 0;



    // 각 체크된 체크박스에 대해 상품 가격 * 수량과 배송비를 계산하여 합계에 더하기
    checkedCheckboxes.forEach(function (checkbox) {
        var row = checkbox.closest('tr');
        var price = parseFloat(row.querySelector('td:nth-child(4)').textContent.replace('원', '').replace(',', ''));
        var deliveryCharge = parseFloat(row.querySelector('td:nth-child(7)').textContent.replace('원', '').replace(',', ''));
        var quantity = parseInt(row.querySelector('td:nth-child(6) .quantity-display').textContent);

        console.log("Price: " + price + ", Delivery Charge: " + deliveryCharge + ", Quantity: " + quantity);

        totalProductAmount += price * quantity;

        // 배송비를 상품의 배송비로 변경
        totalDeliveryCharge += deliveryCharge;


        row.querySelector('td:nth-child(8)').textContent = (price * quantity).toLocaleString() + '원';
    });

    console.log("Total Product Amount: " + totalProductAmount + ", Total Delivery Charge: " + totalDeliveryCharge);


    // 총 상품 구매 금액이 10만원 이상인 경우 배송비를 0으로 설정
    if (totalProductAmount >= 100000) {
        totalDeliveryCharge = 0;
    }

    // 전체 합계를 계산하여 HTML에 표시
    var totalSumElement = document.getElementById('totalPrice');
    totalSumElement.textContent = totalProductAmount.toLocaleString() + '원';

    var deliveryChargeElement = document.getElementById('deliveryCharge');
    deliveryChargeElement.textContent = totalDeliveryCharge.toLocaleString() + '원';

    var totalOrderElement = document.getElementById('totalOrder');
    totalOrderElement.textContent = (totalProductAmount + totalDeliveryCharge).toLocaleString() + '원';

}

