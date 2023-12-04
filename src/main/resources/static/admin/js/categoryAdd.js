 
// 카테고리 대분류 추가
$("body").on("click", "#btnCategoryAdd", function(event) {
    event.preventDefault(); // 링크의 기본 동작 방지
    console.log(`버튼 클릭`)
    // 새로운 엘리먼트 생성
    var newCategory = $(
        "<table style='width: 550px;' class='category-row'>" +
        "<colgroup>" +
        "<col style='width: 85%;'>" +
        "<col style='width: 5%;'>" +
        "<col style='width: 5%;'>" +
        "<col style='width: 5%;'></colgroup>" +
        "<tr class='category'>" +
        "<td><img src='../../image/category1.png' height='20px'> 카테고리(대분류)</td>" +
        "<td><a href='#'><img src='../../image/add2.png' height='13px' name='img-add2' style='padding-left: 5px;'></a></td>" +
        "<td><a href='#'><img src='../../image/open.png' height='12px' name='img-open' id='img-open'></a></td>" +
        "<td><a href='#'><img src='../../image/allow_down.png' height='10px' name='img-allow_down'></a></td>" +
        "</tr>"
    );

    $(".category-row:last").after(newCategory);
});


// 카테고리 소분류 추가
    $("body").on("click", 'img[name="img-add2"]', function() {
    console.log(`버튼 클릭`)
    // 새로운 엘리먼트 생성
    var currentRow = $(this).closest('tr');
    var newsemiCategory = $(
        "<tr class='category1'>"+
        "<td style='padding-left: 5px;'><img src='../../image/L.png' height='20px'><img src='../../image/category2.png' height='20px'> 카테고리(소분류)</td>"+
        "<td></td>"+
        "<td></td>"+
        "<td><a href='#'><img src='../../image/open.png' height='12px' name='open'></a></td>"+
        "</tr>"
    );

    currentRow.after(newsemiCategory);
});

//화살표에 따라서 소분류 카테고리 보이고 숨기기
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
            clickedImage.attr('src', '../../image/allow_up.png');
            clickedImage.attr('name', 'img-allow_up');
            hiderow.nextAll('.category1').show();
            break;
        case 'img-allow_up':
            clickedImage.attr('src', '../../image/allow_down.png');
            clickedImage.attr('name', 'img-allow_down');
            hiderow.nextAll('.category1').hide();
            break;        
        case 'img-close':
            clickedImage.attr('src', '../../image/open.png');
            clickedImage.attr('name', 'img-open');
            break;
        case 'img-open':
            clickedImage.attr('src', '../../image/close.png');
            clickedImage.attr('name', 'img-close');
    }
}

