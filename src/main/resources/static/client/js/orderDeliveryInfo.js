document.addEventListener('DOMContentLoaded', function () {
    $('#submit_button').click(
        function () {
            $('form').submit();
        }
    )
});

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
