$(document).ready(function () {

    // input_option 값이 변경될 때의 이벤트 처리
    $('#input_option').on("change", function () {
        var selectedOption = $(this).val();
        var deliveryInquiryContent = null;
        // 배송문의인 경우 Summernote 내용을 설정
        if (selectedOption === '1') {
            deliveryInquiryContent = "주문번호를 입력하시면 더 빠른 답변을 받으실 수 있습니다.";
            alert(deliveryInquiryContent);
        } else if (selectedOption === '2') { //상품문의
            deliveryInquiryContent = "제품이름을 입력하시면 더 빠른 답변을 받으실 수 있습니다.";
            alert(deliveryInquiryContent);
        } else if (selectedOption === '3') { //교환문의
            deliveryInquiryContent = "주문번호를 입력하시면 더 빠른 답변을 받으실 수 있습니다. \n교환 원하시는 제품의 재고가 부족할시 환불처리됩니다.";
            alert(deliveryInquiryContent);
        } else if (selectedOption === '4') { //환불문의
            deliveryInquiryContent = "주문번호를 입력하시면 더 빠른 답변을 받으실 수 있습니다. \n환불처리는 7 ~ 10일 정도 소요됩니다.";
            alert(deliveryInquiryContent);
        } else if (selectedOption === '5') { //기타문의
            deliveryInquiryContent = "";
        } else if (selectedOption === '0') { //기타문의
            deliveryInquiryContent = "문의 분류를 선택해주세요. ";
            alert(deliveryInquiryContent);
        }
    });

    // Summernote 초기화
    $('#summernote').summernote({
        placeholder: "",
        tabsize: 2,
        height: 300,
        minHeight: null,
        maxHeight: null,
        focus: false,
        lang: "ko-KR",
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ],
        callbacks: {
            onImageUpload: function (files) {
                uploadSummernoteImageFile(files[0], this);
            },
            onPaste: function (e) {
                var clipboardData = e.originalEvent.clipboardData;
                if (clipboardData && clipboardData.items && clipboardData.items.length) {
                    var item = clipboardData.items[0];
                    if (item.kind === 'file' && item.type.indexOf('image/') !== -1) {
                        e.preventDefault();
                    }
                }
            },
            // onMediaDelete: function (target, editor, editable) {
            //     handleDeletedImage(target);
            // },
            // onKeyDown: function (e) {
            //     console.log(e);
            //     if (e.key === "Backspace") {
            //         handleBackspaceKey();
            //     }
            // }
        }
    });

    function uploadSummernoteImageFile(file, editor) {
        data = new FormData();
        data.append("file", file);

        $.ajax({
            data: data,
            type: "POST",
            url: "/board/uploadSummernoteImageFile",
            contentType: false,
            processData: false,
            success: function (data) {
                // 반환받는 data의 url 값은
                // <img th:src="|/thumbPath${order.convertPath}|" th:alt="|${order.orderCode}번 제품이미지|">
                // 여기서 /thumbPath를 포함한 값이다. 하지만 데이터베이스에 저장되는건 뒤의 $ 부분
                addImageInfoToHiddenInput(data.convertPath, data.savePath, data.sourceName, data.convertName);
                $(editor).summernote('insertImage', data.url);
                console.log(data);
            }
        })
    }

    function addImageInfoToHiddenInput(convertPath, savePath, sourceName, convertName) {
        var hiddenInputValue = $("#uploadedImages").val();
        var newImageInfo = {
            convertPath: convertPath,
            savePath: savePath,
            sourceName: sourceName,
            convertName: convertName
        };
        try {
            var updatedValue = hiddenInputValue ? JSON.parse(hiddenInputValue) : [];
        } catch (e) {
            console.error('Error parsing JSON:', e);
            console.error('JSON String:', hiddenInputValue);
        }
        updatedValue.push(newImageInfo);

        $("#uploadedImages").val(JSON.stringify(updatedValue));
    }

    // function handleDeletedImage(target) {
    //     var convertPath = target.attr('src').replace('/thumbPath', '');
    //     console.log(convertPath, "삭제하려던 값의 src값. 근데 /thumbPath가 빠진.")
    //
    //     removeImageInfoFromHiddenInput(convertPath);
    // }
    //
    // function removeImageInfoFromHiddenInput(convertPath) {
    //     var hiddenInputValue = $('#uploadedImages').val();
    //     console.log(hiddenInputValue, "삭제 전 히든input")
    //     if (hiddenInputValue) {
    //         var existingImageInfo = JSON.parse(hiddenInputValue);
    //         var updatedValue = existingImageInfo.filter(function (imageInfo) {
    //             return imageInfo.convertPath !== convertPath;
    //         });
    //         $('#uploadedImages').val(JSON.stringify(updatedValue));
    //     }
    //     console.log($('#uploadedImages').val(), "삭제 후 히든input")
    // }
    //
    // function handleBackspaceKey() {
    //     console.log("backspace를 눌렀다...")
    //     var currentNode = $('#summernote').summernote('focus').summernote('editable').data('target');
    //
    //     if (currentNode && currentNode[0].nodeName === 'IMG') {
    //         // 이미지를 삭제하는 로직
    //         handleDeletedImage($(currentNode));
    //     }
    // }
});