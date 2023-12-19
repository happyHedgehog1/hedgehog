$(document).ready(function () {
    // Summernote 초기화
    $('#summernote').summernote({
        placeholder: 'Hello stand alone ui',
        tabsize: 2,
        height: 300,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture', 'video']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ]
    });

    // 등록 버튼 클릭 시 실행될 함수
    $('#btn').on('click', function () {
        // Summernote의 내용을 가져와서 content에 설정
        var summernoteContent = $('#summernote').summernote('code');
        $('#content').val(summernoteContent);

        // Ajax를 사용하여 서버로 데이터 전송
        $.ajax({
            type: 'POST',
            url: '/Service/inquiryComment',
            data: {
                content: summernoteContent
            },
            success: function (response) {
                console.log('Success:', response);
                // 성공적으로 서버에 데이터를 전송한 후의 동작을 추가
                // 팝업 닫고 페이지 이동 등
                window.close();
                window.location.href = '/admin/content/Service/Product-inquiry';
            },
            error: function (error) {
                console.log('Error:', error);
                // 서버 전송 중 오류가 발생한 경우의 동작을 추가
                // 실패 시 원하는 처리를 추가
            }
        });
    });
});
