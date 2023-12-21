$('#summernote').summernote({
    tabsize: 2,
    height: 300,
    toolbar: [
      ['style', ['style']],
      ['font', ['bold', 'underline', 'clear']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link', 'picture']],
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
        }
    }
  });

function uploadSummernoteImageFile(file, editor) {
    data = new FormData();
    data.append("file", file);

    $.ajax({
        data: data,
        type: "POST",
        url: "/autoMailModify/uploadSummernoteImageFile",
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

