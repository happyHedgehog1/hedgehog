document.addEventListener('DOMContentLoaded', function () {
    // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
    var modifyButtons = document.querySelectorAll('.proSearchBottomtr');
    modifyButtons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            // 기본 동작 중지
            event.preventDefault();

            // 팝업창 열기
            openPopup('/admin/Service/Product-inquiry-details.html', 1000, 800);
        });
    });

        // 팝업창 열기 함수
        function openPopup(url, width, height) {
            // 팝업창 중앙 정렬을 위한 좌표 계산
            var left = window.innerWidth / 2 - width / 2;
            var top = window.innerHeight / 2 - height / 2;
    
            // 팝업창 열기
            window.open(
                url,
                '_blank',
                'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left
            );
        }
    });

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