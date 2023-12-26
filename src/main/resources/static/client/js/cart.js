

document.addEventListener("DOMContentLoaded", function () {
    var decreaseButtons = document.querySelectorAll('.decrease-button');
    decreaseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 현재 수량 요소 가져오기
            var quantityElement = this.nextElementSibling;
            var hdElement = quantityElement.nextElementSibling; // hidden field
            // 현재 수량 가져오기
            var currentQuantity = parseInt(quantityElement.innerText);
            // 새로운 수량 계산
            var newQuantity = Math.max(currentQuantity - 1, 1);
                // 수량 표시 업데이트
            quantityElement.innerText = newQuantity;
            hdElement.value = newQuantity;
        });
    });

    // 증가 버튼에 대한 이벤트 리스너 등록
    var increaseButtons = document.querySelectorAll('.increase-button');
    increaseButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            // 현재 수량 요소 가져오기
            var quantityElement = this.previousElementSibling.previousElementSibling;
            var hdElement = quantityElement.nextElementSibling;
            // 현재 수량 가져오기
            var currentQuantity = parseInt(quantityElement.innerText);

            // 새로운 수량 계산
            var newQuantity = currentQuantity + 1;

            // 수량 표시 업데이트
            quantityElement.innerText = newQuantity;
            hdElement.value = newQuantity;

        });
    });
    // 각 수량 hidden field에 대한 초기화 (기본 수량 설정)
    var hdAmountElements = document.querySelectorAll('[name="hdAmount"]');
    hdAmountElements.forEach(function (hdElement) {
        // 현재 값이 없다면 기본 값을 설정 (1로 설정하거나, 필요에 따라서 다르게 설정)
        if (hdElement.value === "") {
            hdElement.value = 1; // 기본 값으로 1 설정
            // 수량을 표시하는 요소도 업데이트
            var quantityDisplayElement = hdElement.previousElementSibling;
            quantityDisplayElement.innerText = 1;
        }
    });

});


// 여기까지 체크박스 수량 수정 //



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

// ======================전체상품 & 선택상품 주문하기 버튼 누르면 실행 ==========================

document.addEventListener('DOMContentLoaded', function () {

    var selectAllItemsButton = document.getElementById('selectAllItems');
    selectAllItemsButton.addEventListener('click', function () {
        selectAllItems();

    });

    // '선택상품주문하기' 버튼에 대한 이벤트 리스너 등록
    var selectOrderButton = document.getElementById('selectOrderButton');
    selectOrderButton.addEventListener('click', function () {
        selectOrderItems();

    });
});

// 전체상품 선택버튼
function selectAllItems() {
    var checkboxes = document.querySelectorAll('[name="cartcheckbox"]');

    // 체크박스의 상태를 변경
    checkboxes.forEach(function (checkbox) {
        checkbox.checked = true;
    });
    updateTotalPrice();

}

//선택상품 선택버튼
function selectOrderItems(){
    var selectedItems = getSelectedItems();
    updateTotalPrice();
    console.log('선택상품주문하기 버튼에서 선택된 상품들:', selectedItems);

}

document.addEventListener("DOMContentLoaded", function () {
    // 삭제 버튼에 클릭 이벤트 리스너 등록
    var deleteButton = document.getElementById('deleteSelectedItems');
    if (deleteButton) {
        deleteButton.addEventListener('click', function () {
            deleteSelectedItems();
        });
    }
});

function deleteSelectedItems() {
    console.log("deleteSelectedItems 나오냐");
    var checkedCheckboxes = document.querySelectorAll('.cart_table input[name="cartcheckbox"]:checked');
    var cartCodes = Array.from(checkedCheckboxes).map(function (checkbox) {
        return checkbox.nextElementSibling.value;
    });

    // 서버로 cartCodes를 전송하고 삭제 작업을 수행하는 fetch 요청을 보냅니다.
    fetch('/basket/cart/delete', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cartCodes),
    })
        .then(response => response.text())
        .then(data => {
            console.log(data);
            if (data === 'success') {
                // 선택된 상품이 성공적으로 삭제된 경우
                cartCodes.forEach(function (code) {
                    removeItemFromUI(code);
                });
                alert('선택된 상품이 삭제되었습니다.');
                location.reload();// 삭제하고 페이지를 리로드함 비동기로 하고싶었으나 적용이 잘 안됨
            } else {
                // 삭제 중 오류가 발생한 경우
                alert('상품 삭제 중 오류가 발생했습니다.');
            }
        })
        .catch(error => {
            // AJAX 요청 실패 시
            console.error('Error:', error);
        });
// 화면에서 특정 cartCode에 해당하는 행을 제거하는 함수
function removeItemFromUI(cartCode) {
    var rowToRemove = document.querySelector('.cart_table tr[data-cart-code="' + cartCodes + '"]');
    if (rowToRemove) {
        rowToRemove.remove();
        updateTotalPrice();
    }
}
}



//=========================================


//선택상품 구매하기 alert창
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

// 체크박스에 클릭에 대한 값 변화
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

//여기까지







function updateTotalPrice() {
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


    // 총 상품 구매 금액이 10만원 이상인 경우 배송비 0으로 설정
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




