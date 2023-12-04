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

// 옵션 추가 삭제
$("body").on("click", "li.w140 a", function(event) {
    event.preventDefault(); // 링크의 기본 동작 방지
    if ($(this).find("img").attr("name") === "img-add") {
        // 새로운 op-list 엘리먼트 생성
        var newOpList = $("<tr class='optionList'>" +
                            "<th colspan='2'>" +
                                "<ul class='arrAlign'>" +
                                    "<li class='w400'><input type='text' name='optionName' style='width: 350px;' value='예시 : 색상'></li>" +
                                    "<li class='w400'><input type='text' name='optionColor' style='width: 350px;' value='예시 : 갈색'></li>" +
                                    "<li class='w200'><input type='number' name='optionPrice' style='width: 125px;' value='0'> 원</li>" +
                                    "<li class='w200'><input type='number' name='optionQuantity' style='width: 125px;' value='0'> 개</li>" +
                                    "<li class='w140' style='padding-top: 5px;'><a href='#'><img src='../../image/delete.png' height='25px' name='img-delete'></a></li>" +
                                "</ul>" +
                            "</th>" +
                        "</tr>");

        $(".optionList:last").after(newOpList);
    } else if ($(this).find("img").attr("name") === "img-delete") {
        $(this).closest(".optionList").remove();
    }
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

                // 최대 5개까지만 허용
                if (files.length > 5) {
                    alert("최대 5개의 이미지만 선택할 수 있습니다.");
                    // 선택된 파일 초기화
                    $("#sub_thumbnail").val('');
                    return;
                }

        // 미리보기를 담을 컨테이너 엘리먼트 가져오기
        var thumbnailContainer = $("#subThumbnailPreview");

        // 이미지가 5개 이상일 때, 맨 앞 이미지를 제거
        if (thumbnailContainer.children('img').length + files.length > 5) {
            var excessCount = thumbnailContainer.children('img').length + files.length - 5;
            thumbnailContainer.children('img:lt(' + excessCount + ')').remove();
        }

        // 새로 추가한 이미지를 배열에 추가
        for (var i = 0; i < files.length; i++) {
            var image = new Image();
            var ImageTempUrl = window.URL.createObjectURL(files[i]);

            image.src = ImageTempUrl;

            image.style.width = '40px';

            // 새로운 이미지를 뒤로 추가
            thumbnailContainer.append(image);
        }
    });
});

$(function(){
    $("#pro_img").change(function(event){
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
$("body").on("input", "input[name='optionQuantity']", function () {
    updateTotalStock();
});

// 전체 재고를 업데이트하는 함수
function updateTotalStock() {
    var totalQuantity = 0;
    $("input[name='optionQuantity']").each(function () {
        var quantity = parseInt($(this).val()) || 0;
        totalQuantity += quantity;
    });

    // 전체 재고를 표시할 요소의 ID가 'totalStock'라고 가정합니다. 만약 다르다면 해당 요소의 ID로 바꿔주세요.
    $("#totalStock").text(totalQuantity);
}

// 페이지 로딩 시 초기 전체 재고를 설정하기 위해 updateTotalStock 함수를 호출합니다.
updateTotalStock();