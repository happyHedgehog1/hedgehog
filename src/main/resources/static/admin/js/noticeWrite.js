$('#summernote').summernote({
    placeholder: '공지사항을 입력하세요',
    tabsize: 2,
    height: 300,
    toolbar: [
      ['style', ['style']],
      ['font', ['bold', 'underline', 'clear']],
      ['color', ['color']],
      ['para', ['ul', 'ol', 'paragraph']],
      ['table', ['table']],
      ['insert', ['link']],
      ['view', ['fullscreen', 'codeview']]
    ]
  });

$(document).ready(function() {
    $('#summernote').summernote();

    // 저장 버튼 클릭 시 Summernote 내용을 서버로 전송
    $('#submit').on('click', function() {
        var summernoteContent = $('#summernote').summernote('code');

        // Ajax를 사용하여 서버로 데이터 전송
        $.ajax({
            type: 'POST',
            url: '/Service/noticeRegister', // 실제 컨트롤러의 엔드포인트로 대체
            data: {
                content: summernoteContent
            },
            success: function(response) {
                console.log('Success:', response);
                // 성공적으로 서버에 데이터를 전송한 후의 동작을 추가
            },
            error: function(error) {
                console.log('Error:', error);
                // 서버 전송 중 오류가 발생한 경우의 동작을 추가
            }
        });
    });
});


