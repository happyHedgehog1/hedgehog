$(document).ready(function () {
    var optionIndex = 1;

    $("body").on("click", "#img-add img", function (event) {
        console.log("클릭");
        event.preventDefault(); // 링크의 기본 동작 방지

        var src = $(this).attr("src");
        if (src.indexOf("add.png") !== -1) {
            // 'delete.png'로 변경
            $(this).attr("src", /*[[ @{/admin/images/delete.png} ]]*/);

            // 새로운 op-list 엘리먼트 생성
            var newOpList = $("<tr class='optionList'>" +
                "<th colspan='2'>" +
                "<ul class='arrAlign'>" +
                "<li className='colorPreview' style='width: 200px'>미리보기</li>" +
                "<li class='w400'><input type='text' name='optionDTO[" + optionIndex + "].optionCode' style='width: 350px;' placeholder='#FFFFFF 형식으로 작성해주세요'></li>" +
                "<li class='w400'><input type='text' name='optionDTO[" + optionIndex + "].optionName' style='width: 350px;' placeholder='예시 : 갈색'></li>" +
                "<li class='w200'><input type='number' name='optionList[" + optionIndex + "].stock' style='width: 125px;' value='0'> 개</li>" +
                "<li class='w140' style='padding-top: 5px;'><img src='/*[[ @{/admin/images/delete.png} ]]*/' height='25px' name='img-delete'></li>" +
                "</ul>" +
                "</th>" +
                "</tr>");

            $(".optionList:last").after(newOpList);
            optionIndex++;
        } else if (src.indexOf("delete.png") !== -1) {
            // 'add.png'로 변경
            $(this).attr("src", /*[[ @{/admin/images/add.png} ]]*/);

            $(this).closest(".optionList").remove();
            optionIndex--;
            updateTotalStock();
        }

        if (optionIndex === 1) {
            $(this).attr("src", /*[[ @{/admin/images/add.png} ]]*/);
        }
    });

    $("body").on("click", "img[name='img-delete']", function (event) {
        $(this).closest("tr.optionList").remove();
    });

    $("#file").on("change", function (e) {
        var files = e.target.files;
        var i, f;
        for (i = 0; i != files.length; ++i) {
            f = files[i];
            var reader = new FileReader();
            reader.onload = function (e) {};
            reader.readAsBinaryString(f);
        }
    });

    // 이미지 미리보기 업데이트
    function updateImagePreview(inputId, previewId, maxWidth) {
        $("#" + inputId).change(function (event) {
            const file = event.target.files;

            var image = new Image();
            var ImageTempUrl = window.URL.createObjectURL(file[0]);

            image.src = ImageTempUrl;

            image.style.width = maxWidth + 'px';

            // 이전 이미지를 제거하고 새로운 이미지를 추가
            $("#" + previewId).empty().append(image);
        });
    }

    // 썸네일 미리보기 업데이트
    updateImagePreview("thumbnail", "thumbnailPreview", 200);

    // 서브 썸네일 미리보기 업데이트
    updateImagePreview("sub_thumbnail", "subThumbnailPreview", 100);

    // 제품 이미지 미리보기 업데이트
    updateImagePreview("proImg", "proPreview", 200);

    // 옵션 수량이 변경될 때 전체 재고 업데이트
    $("#productAdd").on("input", "input[name='optionList[0].stock']", function () {
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
    // 페이지 로딩 시 초기 옵션 개수에 따라 재고 설정
    updateTotalStock();

    var selectedSubCategory = /*[[${product.category.subCategoryName}]]*/ null;

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
    setSelectBox($('#upperCategoryCode'));

    // 상위 카테고리가 변경될 때마다 서브 카테고리 업데이트
    $('#upperCategoryCode').change(function () {
        setSelectBox($(this));
    });
});
