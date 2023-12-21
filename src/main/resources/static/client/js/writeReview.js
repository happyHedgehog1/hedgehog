function setRatings(length) {
    const stars = document.querySelectorAll(".star");
    const emptyStar = document.querySelector("#empty_star").src;
    const fullStar = document.querySelector("#full_star").src;
    for (let i = 0; i <= length; i++) {
        stars[i].src = fullStar;
    }
    for (let i = length + 1; i < 5; i++) {
        stars[i].src = emptyStar;
    }
    $('#stars').val(length + 1);
}

$(document).ready(function () {
    $('#registReview').submit(function (event) {
        var stars = $("input[name='stars']").val();
        if (stars == '0') {
            event.preventDefault();
            alert("별점을 입력하세요.");
        } else if ($('#summernote').val().length === 0) {
            event.preventDefault();
            alert('리뷰 내용을 입력해주세요.');
        }
    })

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