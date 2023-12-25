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

    var files = e.target.files;
    var i,f;
    for (i = 0; i != files.length; ++i) {
        f = files[i];
        var reader = new FileReader();


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

        var thumbnailContainer = $("#subThumbnailPreview");

        if (thumbnailContainer.children('img').length + files.length > 3) {
            var excessCount = thumbnailContainer.children('img').length + files.length - 3;
            thumbnailContainer.children('img:lt(' + excessCount + ')').remove();
        }

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

$(document).ready(function () {
    setSelectBox($('#upperCategoryCode'));

    // 상위 카테고리를 선택하면 서브 카테고리 값을 db에서 가져옴
    $('#upperCategoryCode').change(function () {
        setSelectBox($(this));
    })

    document.getElementById("btnAdd").addEventListener("click",  (e) => {
        e.preventDefault();
        let isValidate = validate();
        if (isValidate) {
            document.getElementById("productAdd").submit();
        }
    });
});



//유효성 검사
    function validate() {
        const salesStart = document.getElementById("salesStart");
        const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
        if (!dateRegex.test(salesStart.value) || salesStart.value === "" || salesStart.value === '' || salesStart.value === null) {
            alert("상품 판매 시작일을 정해주세요");
            salesStart.focus();
            return false;
        }
        const salesEnd = document.getElementById("salesEnd");
        if (!dateRegex.test(salesEnd.value) || salesEnd.value === "" || salesEnd.value === '' || salesEnd.value === null) {
            alert("상품 판매 종료일을 정해주세요");
            salesEnd.focus();
            return false;
        }
        const productName = document.getElementById("productName");
        if (productName.value.length <= 0) {
            alert("상품명을 입력해주세요.");
            productName.focus();
            return false;
        }
        var parsedPrice = parseInt(document.getElementById("price").value);
        if (isNaN(parsedPrice) || parsedPrice <= 0) {
            alert("유효한 가격을 입력하세요.");
            parsedPrice.focus();
            return false;
        }
        const upperCategoryCode = document.getElementById("upperCategoryCode");
        if (upperCategoryCode.value === 0) {
            alert("상위 카테고리를 선택해주세요.");
            upperCategoryCode.focus();
            return false;
        }
        const subCategoryName = document.getElementById("subCategoryName");
        if (subCategoryName.value === "") {
            alert("세부 카테고리를 선택해주세요.");
            subCategoryName.focus();
            return false;
        }
        const optionCode = document.getElementById("optionDTO[0].optionCode");
        if (optionCode.value.length <= 0) {
            alert("색상코드를 입력해주세요.");
            optionCode.focus();
            return false;
        }
        const optionName = document.getElementById("optionDTO[0].optionName");
        if (optionName.value.length <= 0) {
            alert("색상명을 입력해주세요.");
            optionName.focus();
            return false;
        }

        var stock = parseInt(document.getElementById("optionList[0].stock").value);
        if (isNaN(stock) || stock <= 0) {
            alert("유효한 재고량을 입력하세요.");
            stock.focus();
            return false;
        }
        const thumbnail = document.getElementById("thumbnail");
        if (thumbnail.value.length <= 0) {
            alert("대표이미지를 넣어주세요.");
            thumbnail.focus();
            return false;
        }
        const sub_thumbnail = document.getElementById("sub_thumbnail");
        if (sub_thumbnail.value.length <= 0) {
            alert("sub_thumbnail 입력해주세요.");
            sub_thumbnail.focus();
            return false;
        }
        const proImg = document.getElementById("proImg");
        if (proImg.value.length <= 0) {
            alert("proImg 입력해주세요.");
            proImg.focus();
            return false;
        }
        alert("상품이 등록되었습니다.");
        return true;
    }




