$('#summernote').summernote({
  placeholder: '',
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


$(document).ready(function() {
    // Summernote 초기화
    var summernote = $('#summernote').summernote();

    // input_option 값이 변경될 때의 이벤트 처리
    $('#input_option').change(function() {
        var selectedOption = $(this).val();

        // 배송문의인 경우 Summernote 내용을 설정
        if (selectedOption === '1') {
            var deliveryInquiryContent = "주문번호를 입력하시면 더 빠른 답변을 받으실 수 있습니다. <br> 주문번호 : ";
            summernote.summernote('code', deliveryInquiryContent);
        } else if(selectedOption === '2'){ //상품문의 
            var deliveryInquiryContent = "문의 제품명 : <br> 문의 내용 : ";
            summernote.summernote('code', deliveryInquiryContent);
        }else if(selectedOption === '3'){ //교환문의 
            var deliveryInquiryContent = "주문번호를 입력하시면 더 빠른 답변을 받으실 수 있습니다. <br>교환 원하시는 제품의 재고가 부족할시 환불처리됩니다. <br><br> 주문번호 : ";
            summernote.summernote('code', deliveryInquiryContent);
        }else if(selectedOption === '4'){ //환불문의 
            var deliveryInquiryContent = "주문번호를 입력하시면 더 빠른 답변을 받으실 수 있습니다. <br>환불처리는 7 ~ 10일 정도 소요됩니다. <br><br> 주문번호 : ";
            summernote.summernote('code', deliveryInquiryContent);
        }else if(selectedOption === '5'){ //기타문의 
            var deliveryInquiryContent = " ";
            summernote.summernote('code', deliveryInquiryContent);
        }else if(selectedOption === '0'){ //기타문의 
            var deliveryInquiryContent = "문의 분류를 선택해주세요. ";
            summernote.summernote('code', deliveryInquiryContent);
        }
        
    });
});