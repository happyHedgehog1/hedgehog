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
        document.getElementById('totalPrice').textContent = totalSum.toLocaleString() + '원';
        document.getElementById('deliveryCharge').textContent = deliveryCharge.toLocaleString() + '원';

        // 결제 예정 금액 업데이트
        var totalOrder = totalSum + deliveryCharge;
        document.getElementById('totalOrder').textContent = totalOrder.toLocaleString() + '원';
    }

    // 페이지 로드시 초기 합계 계산 및 업데이트
    updateTotal();
});