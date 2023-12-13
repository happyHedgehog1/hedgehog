// 카테고리 소분류 추가
$("body").on("click", 'img[name="img-add2"]', function () {
    console.log(`버튼 클릭`);
    // 새로운 엘리먼트 생성
    var currentRow = $(this).closest('tr');
    var newsemiCategory = $(
        "<tr class='category1'>" +
        "<td style='padding-left: 5px;'><img src='/admin/images/L.png' height='20px'><img src='/admin/images/category2.png' height='20px'> 카테고리(소분류)</td>" +
        "<td></td>" +
        "<td></td>" +
        "</tr>"
    );

    currentRow.after(newsemiCategory);
    // 여기서 클릭 이벤트 추가
    setupRowEvents(newsemiCategory);
});

// 화살표에 따라서 소분류 카테고리 보이고 숨기기
$(document).ready(function () {
    // 이미지를 클릭했을 때
    $('body').on('click', 'img[name^="img-"]', function () {
        // 클릭한 이미지에 따라 상태 변경
        toggleImageState($(this));
    });
});

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

    $('.categoryTable').on('click', 'td', function () {
        const productCode = this.querySelector('.category1 img[name="open"]').getAttribute('data-product-code');
        // productCode를 이용한 원하는 동작 수행
        console.log("Product Code: " + productCode);

    });
}

// categoryTable 온클릭 스크립트
if (document.querySelectorAll("#categoryTable td")) {
    const $tdsProduct = document.querySelectorAll("#categoryTable td");

    for (let i = 0; i < $tdsProduct.length; i++) {
        setupRowEvents($tdsProduct[i].closest('tr'));
    }
}
$(document).ready(function () {
    setupRowEvents();
});

function sendDataToController(categoryCode) {
    $.ajax({
        type: 'GET',
        url: '/category/categoryDetail',  // 실제로 사용할 URL로 변경
        data: {categoryCode: categoryCode},
        success: function (response) {
            // 성공 시 수행할 동작
            console.log('Data sent successfully');
        },
        error: function (error) {
            // 오류 시 수행할 동작
            console.error('Error sending data:', error);
        }
    });
}


