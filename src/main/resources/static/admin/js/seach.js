
    $(document).ready(function () {
    // 상위 체크박스를 클릭할 때
    $(".searchResult th input[type='checkbox']").change(function () {
        // 해당 상위 체크박스의 체크 상태를 가져옴
        var isChecked = $(this).prop("checked");

        // 하위 체크박스들의 상태를 업데이트
        $(".proSearchBottomtr input[type='checkbox']").prop("checked", isChecked);

        // 하위 체크박스들의 상태를 업데이트
        $(".searchBottom_tr input[type='checkbox']").prop("checked", isChecked);

    });


});


    $(document).ready(function () {
        // 상위 체크박스를 클릭할 때
        $(".eventSearchResult th input[type='checkbox']").change(function () {
            // 해당 상위 체크박스의 체크 상태를 가져옴
            var isChecked = $(this).prop("checked");

            // 하위 체크박스들의 상태를 업데이트
            $(".eventSearchBottomTr input[type='checkbox']").prop("checked", isChecked);

        });


    });



