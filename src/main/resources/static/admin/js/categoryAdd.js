
// 클릭된 이미지에 따라 행 이벤트 설정
function setupRowEvents(row) {
    // Use delegation on the parent table
    $('.categoryTable').on('mouseenter', 'td', function () {
        this.style.backgroundColor = "#A7727D";
        this.style.color = "white";
        this.style.cursor = "pointer";
    });

    $('.categoryTable').on('mouseout', 'td', function () {
        this.style.backgroundColor = "white";
        this.style.color = "black";
    });
}

// 카테고리 소분류 추가
// $("body").on("click", 'img[name="img-add2"]', function () {
//     console.log(`버튼 클릭`);
//     // 새로운 엘리먼트 생성
//     var currentRow = $(this).closest('tr');
//     var categoryName = currentRow.find('input[type="hidden"]').val()
//     console.log(categoryName + "!!!!!!!!!!!!!!!!!!!!!!!!!!!")
//     var newsemiCategory = $(
//         "<tr class='category1'> <input type=\"hidden\" th:value='" + categoryName + "'/>" +
//         "<td style='padding-left: 5px;'><img src='/admin/images/L.png' height='20px'><img src='/admin/images/category2.png' height='20px'> 카테고리(소분류)</td>" +
//         "<td></td>" +
//         "<td></td>" +
//         "</tr>"
//     );

//
//     currentRow.after(newsemiCategory);
//     // 여기서 클릭 이벤트 추가
//     setupRowEvents(newsemiCategory);
//     console.log(newsemiCategory)
//
// });

// 화살표에 따라서 소분류 카테고리 보이고 숨기기
// $(document).ready(function () {
//     // 이미지를 클릭했을 때
//     $('body').on('click', 'img[name^="img-"]', function () {
//         // 클릭한 이미지에 따라 상태 변경
//         toggleImageState($(this));
//     });
// });

// 카테고리의 이미지 상태를 변경하는 함수
function toggleImageState(clickedImage) {
    // 클릭한 이미지의 이름에 따라 분기
    var imageName = clickedImage.attr('name');
    var hiderow = clickedImage.closest('tr');

    switch (imageName) {
        case 'img-allow_down':
            clickedImage.attr('src', '/admin/images/allow_up.png');
            clickedImage.attr('name', 'img-allow_up');
            hiderow.nextAll('.category1').show();
            break;
        case 'img-allow_up':
            clickedImage.attr('src', '/admin/images/allow_down.png');
            clickedImage.attr('name', 'img-allow_down');
            hiderow.nextAll('.category1').hide();
            break;
    }
}

// categoryTable 온클릭 스크립트
$(document).ready(function () {
    setupRowEvents(); // 테이블 전체에 대한 이벤트 설정

    // 각 행에 대한 이벤트 추가
    $('.categoryTable').on('click', 'td', function () {


        // 선택된 행의 categoryName 가져오기
        var categoryName = $(this).find('input[type="hidden"]').val()
        console.log('클릭한 셀의 내용 : ' + categoryName)

        // AJAX 요청 수행
        $.ajax({
            type: 'GET',
            url: '/category/categoryDetail',
            data: { categoryName: categoryName },
            success: function (response) {
                // 성공 시 수행할 동작
                updateTable2(response);
            },
            error: function (error) {
                // 오류 시 수행할 동작
                console.error('Error sending data:', error);
            }
        });
    })
});

function updateTable2(data) {
    var categoryName = $('#categoryName');
    var upperCategoryCode = $('#upperCategoryCode')
    var subCategoryName = $('#subCategoryName')
    var inputCategoryName = $('#inputCategoryName')
    var categoryDisplayY = $('input[name="categoryDisplay"][value="Y"]');
    var categoryDisplayN = $('input[name="categoryDisplay"][value="N"]');
    var totalCount = $('.totalCount');
    var stateY = $('.stateY');
    var stateN = $('.stateN');

    upperCategoryCode.val('');
    subCategoryName.val('');
    inputCategoryName.val('');
    categoryName.val('');
    totalCount.text('');
    stateY.text('');
    stateN.text('');

    upperCategoryCode.val(data[0].category.upperCategoryCode);
    subCategoryName.val(data[0].category.subCategoryName);
    categoryName.val(data[0].category.name);
    inputCategoryName.val(data[0].category.name);


    if (data[0].category.name === "침실"
        || data[0].category.name === "거실"
        || data[0].category.name === "서재"
        || data[0].category.name === "주방") {
        inputCategoryName.prop('disabled', true);
        categoryDisplayY.prop('disabled', true);
        categoryDisplayN.prop('disabled', true);
        $('#submit').hide();

        totalCount.text(data[0].productCode);
        stateY.text(data[data.length - 1].price);
        stateN.text(data[0].productCode - data[data.length - 1].price);


    } else {
        // 다른 경우에는 활성화
        inputCategoryName.prop('disabled', true);
        categoryDisplayY.prop('disabled', true);
        categoryDisplayN.prop('disabled', true);

        $('#submit').hide();

        // 노출 상태 설정
        if (data[0].category.state === "Y") {
            console.log("Y")
            categoryDisplayY.prop('checked', true);
        } else {
            console.log("N")

            categoryDisplayN.prop('checked', true);
        }
        totalCount.text(data[0].productCode);
        stateY.text(data[data.length - 1].price);
        stateN.text(data[0].productCode - data[data.length - 1].price);


    }


}



$('#submitBtn').on('click', function () {
    var formData = $('#categoryForm').serialize(); //




    $.ajax({
        type: 'POST', // POST 메소드 사용
        url: '/category/categoryModify',
        data: formData,
        success: function (response) {
            // 성공 응답 처리
            console.log('폼이 성공적으로 제출되었습니다:', response);
        },
        error: function (error) {
            // 오류 처리
            console.error('폼 제출 중 오류 발생:', error);
        }
    });
});


