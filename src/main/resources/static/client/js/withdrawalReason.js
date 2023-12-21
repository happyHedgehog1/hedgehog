$(document).ready(function () {
    $('#summernote').summernote({
        placeholder: '탈퇴하고 싶은 이유를 작성하시면 됩니다.',
        height: 350,
        maxHeight: 1000,
        width: 700,
        maxWidth: 700,
        focus: true,
        lang: "ko-KR",
        disableDragAndDrop: true,
        dialogsInBody: true,
        callbacks: {},
        toolbar: [],
    });
    $('#summernote').summernote('code', localStorage.getItem("summernoteContent") || '');
    $('#summernote').on('summernote.keyup', function () {
        const content = $(this).summernote('code');
        const maxLength = 3000;
        console.log(content.length);
        if (content.length > maxLength) {
            alert("글자수가 너무 많습니다. (3000자 이하)");
            $(this).summernote('code', content.substring(0, maxLength));
        }
    })
    $('form').submit(function () {
        $('#summernoteContent').val($('#summernote').summernote('code'))
        localStorage.setItem("summernoteContent", $('#summernote').summernote('code'));
    })
});