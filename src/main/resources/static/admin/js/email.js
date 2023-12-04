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

  function updateCurrentTime() {
    var currentTimeElement = document.getElementById("currentTime");
    var currentTime = new Date();
    var Year = currentTime.getFullYear();
    var Month = currentTime.getMonth();
    var date = currentTime.getDate();


    // 시간을 HTML 요소에 업데이트
    currentTimeElement.textContent = Year + "년 " + Month + "월 " + date + "일";
}

// 페이지 로드 시 초기 업데이트
updateCurrentTime();


