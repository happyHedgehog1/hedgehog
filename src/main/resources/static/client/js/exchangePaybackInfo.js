document.addEventListener('DOMContentLoaded', function () {
    $('#submit_button').click(
        function () {
            $('form').submit();
        }
    )
});

function changeDate(state, dateStart, dateEnd) {
    paging(1, state, dateStart, dateEnd)
}

function paging(currentPage, state, dateStart, dateEnd) {
    const url = '/myshop/exchangePaybackInfo' +
        '?currentPage=' + currentPage +
        '&state=' + state +
        '&dateStart=' + dateStart +
        '&dateEnd=' + dateEnd;
    window.location.href = url;
}

$(document).click(function () {
    const dateStartInput = $('#date_start');
    const dateEndInput = $('#date_end');

    dateStartInput.on('change', function () {
        const dateStart = new Date(dateStartInput.val());
        const dateEnd = new Date(dateEndInput.val());

        let tomorrow = new Date();

        if (dateStart > tomorrow) {
            alert('시작날짜가 내일 이후가 될 수 없습니다.');
            dateStartInput.val(''); // 다른값으로 대체할 수도 있음.
            return;
        }
        if (dateStart > dateEnd) {
            alert('시작날짜가 끝날짜 이후가 될 수 없습니다.');
            dateEndInput.val(dateStartInput.val());
            return;
        }
    });
    dateEndInput.on('change', function () {
        const dateStart = new Date(dateStartInput.val());
        const dateEnd = new Date(dateEndInput.val());

        let tomorrow = new Date();

        if (dateEnd > tomorrow) {
            alert('끝날짜가 내일 이후가 될 수 없습니다.');
            dateStartInput.val(''); // 다른값으로 대체할 수도 있음.
            return;
        }
        if (dateStart > dateEnd) {
            alert('끝날짜가 시작날짜 이전이 될 수 없습니다.');
            dateStartInput.val(dateEndInput.val());
            return;
        }
    })
})

$(document).ready(function () {
    $('.order_row').click(function () {
        var orderCode = parseInt($(this).data("order-code"));
        console.log(orderCode);

        $.ajax({
            type: "POST",
            url: "/myshop/memberOrderDetails",
            data: {orderCode: orderCode},
            success: function (response) {
                console.log(response);
                if (response === "success") {
                    window.location.href = "/myshop/orderDetails?orderCode=" + orderCode;
                } else {
                    // 실패했을 경우
                }
            },
            error: function (error) {
                console.error("Error:", error);
                // 오류가 나올 경우.
            }
        })
    })
})