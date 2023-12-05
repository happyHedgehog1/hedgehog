
function updateQuantity(productId, amount) {
    const quantityDisplay = document.getElementById(`quantity-display-${productId}`);
    let quantity = parseInt(quantityDisplay.innerText);

    // 수량이 1보다 작을 수 없도록 설정
    quantity = Math.max(1, quantity + amount);

    // 수량을 업데이트하고 화면에 표시
    quantityDisplay.innerText = quantity;
}




document.addEventListener("DOMContentLoaded", function () {
    // th에 있는 체크박스를 선택
    const headerCheckbox = document.querySelector('th input.cartcheckbox');

    // 모든 td에 있는 체크박스를 선택
    const cellCheckboxes = document.querySelectorAll('td input.cartcheckbox');

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


$(document).ready(function(){
    var currentPosition = parseInt($(".quickmenu").css("top"));
    $(window).scroll(function() {
        var position = $(window).scrollTop();
        $(".quickmenu").stop().animate({"top":position+currentPosition+"px"},800);
    });
});