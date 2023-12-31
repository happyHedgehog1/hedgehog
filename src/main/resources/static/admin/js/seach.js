$(document).ready(function () {
    // 상위 체크박스를 클릭할 때
    $(".searchResult th input[type='checkbox']").change(function () {
        // 해당 상위 체크박스의 체크 상태를 가져옴
        var isChecked = $(this).prop("checked");

        // 하위 체크박스들의 상태를 업데이트
        $(".proSearchBottomtr input[type='checkbox']").prop("checked", isChecked);

        // 하위 체크박스들의 상태를 업데이트
        $(".searchBottom_tr input[type='checkbox']").prop("checked", isChecked);

        // 하위 체크박스들의 상태를 업데이트
        $(".eventProdAdd input[type='checkbox']").prop("checked", isChecked);
    });


});


    /**
     * 이벤트 목록 조회 마우스 오버 메소드
     */
    $(document).ready(function () {
        if (document.querySelectorAll("#eventlist td")) {
            const $tdsEvent = document.querySelectorAll("#eventlist td");
            for (let i = 0; i < $tdsEvent.length; i++) {
                $tdsEvent[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsEvent[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsEvent[i].ondblclick = function() {
                    const postCode = this.parentNode.children[0].innerText;
                    location.href = "/event/eventModify?postCode=" + postCode;



                };
            }

        }
        /**
         * 상품조회 온클릭
         */
        if (document.querySelectorAll("#productSearch td")) {
            const $tdsProduct = document.querySelectorAll("#productSearch td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].onclick = function() {
                    const productCode = this.parentNode.children[0].innerText;
                    location.href = "/product/productDetail?productCode=" + productCode;


                };
            }
        }
        /**
         * 탈퇴 회원 온클릭
         */
        if (document.querySelectorAll("#unregister td")) {
            const $tdsProduct = document.querySelectorAll("#unregister td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function() {
                    const userCode = this.parentNode.querySelector("input[name='resultCheckbox']").value;

                    const unregisterDetailUrl = "/unregister/unregisterDetail?userCode=" + userCode;
                    window.open(unregisterDetailUrl, "_blank", "width=840, height=700");
                };
            }
        }


        /**
         * 회원 조회 온클릭
         */
        if (document.querySelectorAll("[name='memberSearch'] td")) {
            const $tdsProduct = document.querySelectorAll("[name='memberSearch'] td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function () {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                };

                $tdsProduct[i].onmouseout = function () {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                };

                $tdsProduct[i].ondblclick = function () {
                    const member_code = this.parentNode.querySelector("input[name='resultCheckbox']").value;

                    const memberDetailUrl = "/member/pointPage?member_code=" + member_code;
                    window.open(memberDetailUrl, "_blank", "width=840, height=500");
                };
            }
        }

        /**
         * 주문 내역 조회 온클릭
         */
        if (document.querySelectorAll("#order td")) {
            const $tdsProduct = document.querySelectorAll("#order td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function () {
                    const orderCode = this.parentNode.querySelector("td:nth-child(3)").innerText;

                    const orderDetailUrl = "/order/orderDetail?orderCode=" + orderCode;
                    window.open(orderDetailUrl, "_blank", "width=1530, height=700");
                };
            }
        }

        /**
         * 상품 문의 온클릭
         */
            if (document.querySelectorAll("#productInquiry td")) {
            const $tdsProduct = document.querySelectorAll("#productInquiry td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function() {
                    const inquiry_code = this.parentNode.querySelector("input[name='resultCheckbox']").value;
                    const answer_state = this.parentNode.children[5].innerText;
                    const inquiryDetailUrl = "/Service/inquiryDetail?inquiry_code=" + inquiry_code+ "&answer_state=" + answer_state;
                    window.open(inquiryDetailUrl, "_blank", "width=900, height=1500");
                };
            }



            }
        /**
         * 상품 후기 온클릭
         */
        if (document.querySelectorAll("#productReview td")) {
            const $tdsProduct = document.querySelectorAll("#productReview td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function() {
                    const Review_code = this.parentNode.querySelector("input[name='resultCheckbox']").value;

                    const reviewDetailUrl = "/Service/reviewDetail?Review_code=" + Review_code;

                    window.open(reviewDetailUrl, "_blank", "width=840, height=700");
                };
            }
        }

        /**
         * FAQ 조회 온클릭
         */
        if (document.querySelectorAll("#FAQ td")) {
            const $tdsProduct = document.querySelectorAll("#FAQ td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function() {
                    const postCode = this.parentNode.querySelector("input[name='resultCheckbox']").value;

                    location.href = "/Service/FAQModifyPage?postCode=" + postCode;

                };
            }
        }

        /**
         * 공지사항 온클릭
         */
        if (document.querySelectorAll("#notice td")) {
            const $tdsProduct = document.querySelectorAll("#notice td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function() {
                    const postCode = this.parentNode.querySelector("input[name='resultCheckbox']").value;

                    location.href = "/Service/noticeModifyPage?postCode=" + postCode;
                };
            }
        }

        /**
         * 메일 발송내역 온클릭
         */
        if (document.querySelectorAll("#emailHistory td")) {
            const $tdsProduct = document.querySelectorAll("#emailHistory td");
            for (let i = 0; i < $tdsProduct.length; i++) {
                $tdsProduct[i].onmouseenter = function() {
                    this.parentNode.style.backgroundColor = "#A7727D";
                    this.parentNode.style.color = "white";
                    this.parentNode.style.cursor = "pointer";
                }

                $tdsProduct[i].onmouseout = function() {
                    this.parentNode.style.backgroundColor = "white";
                    this.parentNode.style.color = "black";
                }

                $tdsProduct[i].ondblclick = function() {

                    const mailCode = this.parentNode.querySelector("input[name='mailCode']").value;

                    const mailDetailUrl = "/autoMailModify/emailDetail?mailCode=" + mailCode;

                    window.open(mailDetailUrl, "_blank", "width=1000, height=1500");
                };
            }
        }
    });

    /**
     * 서브카테고리 동적으로 가져오는 메소드
     * @param select
     */
    function setSelectBox(select) {
        for (let i = 0; i < $('#upperCategoryCode').children().length; i++) {
            if ($('#upperCategoryCode').children().eq(i).is(':selected')) {
                var upperCategoryCode = $('#upperCategoryCode').children().eq(i).val();
                // 선택된 값에 대한 로직 수행
                console.log('Selected upperCategoryCode:', upperCategoryCode);
            }
        }
        // Ajax를 이용하여 서버에서 데이터 가져오기
        $.ajax({
            url: '/product/category/' + upperCategoryCode,
            type: 'GET',
            success: function (data) {
                // 서버에서 받은 데이터를 이용하여 동적으로 콘텐츠 생성
                var subCategorySelect = $("#subCategoryName");
                subCategorySelect.empty(); // 기존 옵션 제거

                // 서버에서 받은 데이터를 이용하여 옵션 추가
                for (var i = 0; i < data.length; i++) {
                    subCategorySelect.append('<option value="' + data[i].subCategoryName + '">' + data[i].name + '</option>');
                }
            },
            error: function (error) {
                console.error('Error fetching data: ', error);
            }
        });
    }

