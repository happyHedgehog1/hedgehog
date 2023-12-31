
// member.js




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


$(document).ready(function () {
    // 상위 체크박스를 클릭할 때
    $(".eventSearchResult th input[type='checkbox']").change(function () {
        // 해당 상위 체크박스의 체크 상태를 가져옴
        var isChecked = $(this).prop("checked");

        // 하위 체크박스들의 상태를 업데이트
        $(".eventSearchBottomTr input[type='checkbox']").prop("checked", isChecked);

    });


});
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

            $tdsEvent[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/Service/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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
                const no = this.parentNode.children[0].innerText;
                // location.href = "/product/productAdd?no=" + no;
                location.href = "/product/productAdd";


            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }

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

            $tdsProduct[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;
                window.open("/product/detail", "_blank", "width=1500,height=1000");
            };
        }
    }
});

