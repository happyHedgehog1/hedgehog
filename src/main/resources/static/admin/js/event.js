var selectedProducts = [];

// 모달 메뉴 열기
document.getElementById("btnEventAdd").addEventListener("click", function () {
    document.getElementById("myModal").style.display = "flex";
});

// 모달 닫기
document.getElementById("closeModal").addEventListener("click", function () {
    document.getElementById("myModal").style.display = "none";
    resetSearchForm();
});

$(document).ready(function () {
    $("#btnPrdSearch").click(function (e) {
        e.preventDefault();
        console.log("작동");

        var prdKeyword = $("#prdKeyword").val();
        var searchValue = $("#searchValue").val();
        let subCategoryName = $("#subCategoryName").val();
        var searchStartPrice = $("#searchStartPrice").val();
        var searchEndPrice = $("#searchEndPrice").val();

        $.ajax({
            type: "POST",
            url: "/event/productSearch",
            data: {
                prdKeyword: prdKeyword,
                searchValue: searchValue,
                subCategoryName: subCategoryName,
                searchStartPrice: searchStartPrice,
                searchEndPrice: searchEndPrice
            },
            success: function (data) {
                console.log("검색 결과:", data);
                updateTable(data);
            },
            error: function (error) {
                console.error("검색 오류:", error);
            }
        });
    });
});

function updateTable(data) {
    var tableBody = $(".modalTable2");
    console.log("tableBody:", tableBody); // 확인용 출력

    tableBody.empty();
    var headRow =
        '<tr class="searchResult">' +
        '<th style="width: 140px;"><input type="checkbox" name="chk_all_product"></th>' +
        '<th style="width: 140px;">상품번호</th>' +
        '<th>상품명</th>' +
        '<th style="width: 200px;">판매가</th>' +
        '<th style="width: 140px;">등록일(수정일)</th>' +
        ' </tr>';

    tableBody.append(headRow);

    for (var i = 0; i < data.length; i++) {
        console.log("반복문 시작");

        var registrationDate = new Date(data[i].registrationDate);
        var formattedDate = registrationDate.toISOString().split('T')[0];
        var formattedPrice = Number(data[i].price).toLocaleString();

        var row = '<tr class="proSearchBottomtr">' +
            '<td><input type="checkbox" name="resultCheckbox" id="resultCheckbox" style="width: 140px;" value="' + data[i].productCode + '"></td>' +
            '<td>' + data[i].productCode + '</td>' +
            '<td>' + data[i].productName + '</td>' +
            '<td>' + formattedPrice + '원</td>' +
            '<td>' + formattedDate + '</td>';

        tableBody.append(row);
    }
}

function resetSearchForm() {
    $("#prdKeyword").val("");
    $("#searchValue").val("");
    $("#subCategoryName").val("");
    $("#searchStartPrice").val("0");
    $("#searchEndPrice").val("0");

    // 검색 결과 테이블 초기화
    var tableBody = $(".modalTable2");
    tableBody.empty();

    // 테이블 헤더 다시 추가
    var headRow =
        '<tr class="searchResult">' +
        '<th style="width: 140px;"><input type="checkbox" name="chk_all_product"></th>' +
        '<th style="width: 140px;">상품번호</th>' +
        '<th>상품명</th>' +
        '<th style="width: 200px;">판매가</th>' +
        '<th style="width: 140px;">등록일(수정일)</th>' +
        ' </tr>';
    tableBody.append(headRow);
}

function setSelectBox(select) {
    for (let i = 0; i < $('#upperCategoryCode').children().length; i++) {
        if ($('#upperCategoryCode').children().eq(i).is(':selected')) {
            var upperCategoryCode = $('#upperCategoryCode').children().eq(i).val();
            // 선택된 값에 대한 로직 수행
            console.log('Selected upperCategoryCode:', upperCategoryCode);
        }
    }

    // Ajax를 이용하여 서버에서 데이터 가져오기
    $.ajax({
        url: '/product/category/' + upperCategoryCode,
        type: 'GET',
        success: function (data) {
            // 서버에서 받은 데이터를 이용하여 동적으로 콘텐츠 생성
            var subCategorySelect = $("#subCategoryName");
            subCategorySelect.empty(); // 기존 옵션 제거

            // 서버에서 받은 데이터를 이용하여 옵션 추가
            for (var i = 0; i < data.length; i++) {
                subCategorySelect.append('<option value="' + data[i].subCategoryName + '">' + data[i].name + '</option>');
            }
        },
        error: function (error) {
            console.error('Error fetching data: ', error);
        }
    });
}

$(document).ready(function () {
    // "chk_all_product" 클릭 시 모든 체크박스 선택/해제
    $('body').on('change', 'input[name="chk_all_product"]', function () {
        var isChecked = $(this).prop('checked');
        $('input[name="resultCheckbox"]').prop('checked', isChecked);
    });

    // 개별 체크박스 상태에 따라 "chk_all_product" 상태 업데이트
    $('body').on('change', 'input[name="resultCheckbox"]', function () {
        var totalCheckboxes = $('input[name="resultCheckbox"]').length;
        var checkedCheckboxes = $('input[name="resultCheckbox"]:checked').length;
        var isAllChecked = totalCheckboxes === checkedCheckboxes;

        $('input[name="chk_all_product"]').prop('checked', isAllChecked);
    });
});

$(document).ready(function () {
    // 이미 추가된 상품 코드를 저장할 배열
    var addedProducts = [];

    // "btnModalProdAdd" 버튼 클릭 시 선택한 상품 추가
    $('#btnModalProdAdd').click(function () {
        // 선택된 체크박스 가져오기
        var selectedCheckboxes = $('input[name="resultCheckbox"]:checked');

        // 중복 추가 여부를 확인하기 위한 플래그
        var isDuplicate = false;

        // 선택된 체크박스의 데이터를 검색 결과 테이블에서 찾아 추가
        selectedCheckboxes.each(function () {
            var productCode = $(this).val();
            var productName = $(this).closest('tr').find('td:eq(2)').text(); // 상품명 열의 데이터 가져오기
            var formattedPrice = $(this).closest('tr').find('td:eq(3)').text(); // 판매가 열의 데이터 가져오기

            // 중복 추가 방지
            if (!isProductAdded(productCode)) {
                // 추가할 행 생성
                var newRow = '<tr class="searchResult">' +
                    '<td><input type="checkbox" name="resultCheckbox"></td>' +
                    '<td style="width: 50px;">' + productCode + '</td>' +
                    '<td>' + productName + '</td>' +
                    '<td style="width: 200px;">' + formattedPrice + '</td>' +
                    '<td style="width: 140px;" style="color: #CD4747"></td>' +
                    '</tr>';

                // 검색 결과 테이블에 추가
                $('.result tbody').append(newRow);

                // 이미 추가된 상품인지 확인하여 기록
                addedProducts.push(productCode);
            } else {
                // 중복 추가되었음을 플래그에 표시
                isDuplicate = true;
            }
        });

        // 중복 추가되었을 경우 알림창 표시
        if (isDuplicate) {
            alert('이미 추가된 상품입니다.');
        } else {
            // 중복이 없을 경우 모달 닫기
            $('#myModal').hide();
            resetSearchForm();

            // 추가 후에 체크박스 초기화
            $('input[name="chk_all_product"]').prop('checked', false);
        }
    });

    // "btnEventDelete" 버튼 클릭 시 선택한 상품 삭제
    $('#btnEventDelete').click(function () {
        // 선택된 체크박스 가져오기
        var selectedCheckboxes = $('.result input[name="resultCheckbox"]:checked');

        // 선택된 상품을 배열에서 제거
        selectedCheckboxes.each(function () {
            var productCode = $(this).closest('tr').find('td:eq(1)').text(); // 상품번호 열의 데이터 가져오기
            removeProductFromList(productCode);
        });

        // 선택된 체크박스의 상위 행 삭제
        selectedCheckboxes.closest('tr').remove();
    });

    // 이미 추가된 상품인지 확인하는 함수
    function isProductAdded(productCode) {
        // 검색 결과 테이블에 이미 추가된 상품인지 확인
        return addedProducts.includes(productCode);
    }

    // 선택한 상품을 배열에서 제거
    function removeProductFromList(productCode) {
        addedProducts = addedProducts.filter(function (code) {
            return code !== productCode;
        });
    }
});

// 모달 외부에서 혜택설정 input 태그에 값을 입력할 때 이벤트 처리
$(document).on('input', '#price', function () {
    // 혜택설정 input 태그의 값 가져오기
    var salenum = $(this).val();

    // 모든 상품에 대해 할인 적용가 계산 및 업데이트
    $('input[name="resultCheckbox"]').each(function () {
        var formattedPrice = $(this).closest('tr').find('td:eq(3)').text(); // 판매가 열의 데이터 가져오기
        var originalPrice = Number(formattedPrice.replace(/[^\d]/g, '')); // 금액에서 쉼표 등을 제외한 숫자만 추출
        var discountedPrice = originalPrice * (1 - salenum / 100); // 할인 적용가 계산
        var formattedDiscountedPrice = discountedPrice.toLocaleString(); // 쉼표를 추가하여 금액 포맷팅

        // 할인 적용가 열 업데이트
        var discountedPriceCell = $(this).closest('tr').find('td:eq(4)');
        discountedPriceCell.text(formattedDiscountedPrice + '원');

        // 빨간색으로 표시
        discountedPriceCell.css('color', '#CD4747');
    });
});

$(document).ready(function () {
    $("#btnEventSubmit").click(function (e) {
        e.preventDefault();
        console.log("작동");

        var status = $("input[name='status']:checked").val();
        var searchStartDay = $("#searchStartDay").val();
        var searchEndDay = $("#searchEndDay").val();
        var price = $("#price").val();
        var allProductCodes = [];
        $('input[name="resultCheckbox"]').each(function () {
            allProductCodes.push($(this).closest('tr').find('td:eq(1)').text()); // 상품번호 열의 데이터 가져오기
        });


        $.ajax({
            type: "POST",
            url: "/event/register",
            contentType: "application/json; charset=UTF-8", // JSON 형식으로 요청을 보내도록 설정
            data: JSON.stringify({
                status: status,
                searchStartDay: searchStartDay,
                searchEndDay: searchEndDay,
                price: price,
                allProductCodes: allProductCodes
            }),
            success: function (data) {
                console.log("검색 결과:", data);
                updateTable(data);
            },
            error: function (error) {
                console.error("검색 오류:", error);
            }
        });
    });
});
