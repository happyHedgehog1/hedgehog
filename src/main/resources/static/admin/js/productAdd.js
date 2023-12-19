document.addEventListener('DOMContentLoaded', function() {


// 모달 메뉴 열기
    document.getElementById("btnSubmitAdd").addEventListener("click", function() {
        document.getElementById("myModal").style.display = "flex";
    });

// 모달 닫기
    document.getElementById("closeModal").addEventListener("click", function() {
        document.getElementById("myModal").style.display = "none";
    });


// 옵션 누르면 메뉴 생겼다 없어졌다가는 기능
$(document).ready(function () {
    $('input[name="option"]').change(function () {
        toggleOptionList();
    });

    // 옵션 목록 토글 함수
    function toggleOptionList() {
        if ($('input[name="option"]:checked').val() === 'Y') {
            $('.optionList').show();
        } else {
            $('.optionList').hide();
        }
    }
});
    var optionIndex = 1;

    $("body").on("click", "#img-add img", function (event) {
        console.log("클릭");
        event.preventDefault(); // 링크의 기본 동작 방지

        var src = $(this).attr("src");
        if (src.includes("add.png")) {
            // 새로운 op-list 엘리먼트 생성
            var newOpList = $("<tr class='optionList'>" +
                "<th colspan='2'>" +
                "<ul class='arrAlign'>" +
                "<li className='colorPreview' style='width: 200px''colorPreview'>미리보기</div></li>"+
                "<li class='w400'><input type='text' name='optionDTO[" + optionIndex + "].optionCode' style='width: 350px;' placeholder='#FFFFFF 형식으로 작성해주세요'></li>" +
                "<li class='w400'><input type='text' name='optionDTO[" + optionIndex + "].optionName' style='width: 350px;' placeholder='예시 : 갈색'></li>" +
                "<li class='w200'><input type='number' name='optionList[" + optionIndex + "].stock' style='width: 125px;' value='0'> 개</li>" +
                "<li class='w140' style='padding-top: 5px;'><img src='/admin/images/delete.png' height='25px' name='img-delete'></li>" +
                "</ul>" +
                "</th>" +
                "</tr>");

            $(".optionList:last").after(newOpList);
            optionIndex++;
        } else if (src.includes("delete.png")) {
            $(this).closest(".optionList").remove();
            optionIndex--;
        }
    });

    $("body").on("click", "img[name='img-delete']", function(event) {
        $(this).closest("tr.optionList").remove();
    });


    $("#file").on("change", function(e){

    var files = e.target.files; //input file 객체를 가져온다.
    var i,f;
    for (i = 0; i != files.length; ++i) {
        f = files[i];
        var reader = new FileReader(); //FileReader를 생성한다.

        //성공적으로 읽기 동작이 완료된 경우 실행되는 이벤트 핸들러를 설정한다.
        reader.onload = function(e) {
        };
        reader.readAsBinaryString(f);


    }
});

$(function(){
    $("#thumbnail").change(function(event){
        const file = event.target.files;

        var image = new Image();
        var ImageTempUrl = window.URL.createObjectURL(file[0]);

        image.src = ImageTempUrl;

        image.style.width = '200px';


        // 이전 이미지를 제거하고 새로운 이미지를 추가
        $("#thumbnailPreview").empty().append(image);
    });
});

$(function () {
    $("#sub_thumbnail").change(function (event) {
        const files = event.target.files;

        // 최대 3개까지만 허용
        if (files.length > 3) {
            alert("최대 3개의 이미지만 선택할 수 있습니다.");
            // 선택된 파일 초기화
            $("#sub_thumbnail").val('');
            return;
        }

        // 미리보기를 담을 컨테이너 엘리먼트 가져오기
        var thumbnailContainer = $("#subThumbnailPreview");

        // 이미지가 5개 이상일 때, 맨 앞 이미지를 제거
        if (thumbnailContainer.children('img').length + files.length > 3) {
            var excessCount = thumbnailContainer.children('img').length + files.length - 3;
            thumbnailContainer.children('img:lt(' + excessCount + ')').remove();
        }

        // 새로 추가한 이미지를 배열에 추가
        for (var i = 0; i < files.length; i++) {
            var image = new Image();
            var ImageTempUrl = window.URL.createObjectURL(files[i]);

            image.src = ImageTempUrl;

            image.style.width = '100px';

            // 새로운 이미지를 뒤로 추가
            thumbnailContainer.append(image);
        }
    });
});

$(function(){
    $("#proImg").change(function(event){
        const file = event.target.files;

        var image = new Image();
        var ImageTempUrl = window.URL.createObjectURL(file[0]);

        image.src = ImageTempUrl;

        image.style.width = '200px';

        // 이전 이미지를 제거하고 새로운 이미지를 추가
        $("#proPreview").empty().append(image);
    });
});



// 옵션 수량이 변경될 때 전체 재고 업데이트
    $("#productAdd").on("input", "input[name^='optionList'][name$='.stock']", function () {
        updateTotalStock();
    });

// 전체 재고를 업데이트하는 함수
    function updateTotalStock() {
        var totalQuantity = 0;

        $("input[name^='optionList'][name$='.stock']").each(function () {
            var quantity = parseInt($(this).val()) || 0;
            totalQuantity += quantity;
        });

        // 전체 stock 업데이트
        $("#totalStock").text(totalQuantity);
    }

// 페이지 로딩 시 초기 전체 재고를 설정하기 위해 updateTotalStock 함수를 호출합니다.
    $(document).ready(function () {
        // 페이지 로딩 시 초기 옵션 개수에 따라 재고 설정
        updateTotalStock();
    });});

var selectedSubCategory = /*[[${product.category.subCategoryName}]]*/ null;
// null 쓰는 이위는 subCategoryName의 값이 없으면 null값을 넣어준다는 의미
// 안쓰면 값이 없을때 오류가 뜰수도있음

function setSelectBox(select) {
    var upperCategoryCode = select.val();

    // Ajax를 이용하여 서버에서 데이터 가져오기
    $.ajax({
        url: '/product/category/' + upperCategoryCode,
        type: 'GET',
        success: function (data) {
            // 서버에서 받은 데이터를 이용하여 동적으로 콘텐츠 생성
            var subCategorySelect = $("#subCategoryName");

            // 기존 옵션 제거
            subCategorySelect.empty();

            // 서버에서 받은 데이터를 이용하여 옵션 추가
            for (var i = 0; i < data.length; i++) {
                var option = $('<option></option>')
                    .attr('value', data[i].subCategoryName)
                    .text(data[i].name);

                // 만약 현재 옵션이 선택된 상태이면 선택 속성 추가
                if (data[i].subCategoryName === selectedSubCategory) {
                    option.attr('selected', 'selected');
                }

                subCategorySelect.append(option);
            }
        },
        error: function (error) {
            console.error('Error fetching data: ', error);
        }
    });
}

// 페이지 로딩 시 초기 서브 카테고리 설정
$(document).ready(function () {
    setSelectBox($('#upperCategoryCode'));

    // 상위 카테고리가 변경될 때마다 서브 카테고리 업데이트
    $('#upperCategoryCode').change(function () {
        setSelectBox($(this));
    });
});
let message = null;

if(message != null && message !== 'null') {
    alert(message);
}
//
// $("#btnAdd").on("click", function () {
//     var salesStart = $("#salesStart").val();
//     var salesEnd = $("#salesEnd").val();
//     var productName = $("#productName").val();
//     var price = parseInt($("#price").val(), 10);
//     var upperCategoryCode = parseInt($("#upperCategoryCode").val(), 10);
//     var optionCode = $("#optionDTO\\[0\\]\\.optionCode").val();  // [ 와 ]는 특수 문자이므로 \\를 추가해 이스케이프 처리
//     var optionName = $("#optionDTO\\[0\\]\\.optionName").val();  // [ 와 ]는 특수 문자이므로 \\를 추가해 이스케이프 처리
//     var stock = $("#optionList\\[0\\]\\.stock").val();  // [ 와 ]는 특수 문자이므로 \\를 추가해 이스케이프 처리
//     var thumbnail = $("#thumbnail").val();
//     var sub_thumbnail = $("#sub_thumbnail").val();
//     var proImg = $("#proImg").val();
//
//     필드 중 하나라도 비어있을 때 경고창 띄우기
//     if (salesStart === '' ||
//         salesEnd === '' ||
//         productName === "" ||
//         price === "" ||
//         upperCategoryCode === "" ||
//         optionCode === "" ||
//         optionName === "" ||
//         stock === "" ||
//         thumbnail === "" ||
//         sub_thumbnail === "" ||
//         proImg === ""
//     ) {
//         alert("모든 필드를 입력하세요.");
//     }
// });
//
//
//
