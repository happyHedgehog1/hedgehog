    if (document.querySelectorAll("#listArea td")) {
        const $tds = document.querySelectorAll("#listArea td");
        for (let i = 0; i < $tds.length; i++) {

            $tds[i].onmouseenter = function() {
                this.parentNode.style.backgroundColor = "#A7727D";
                this.parentNode.style.cursor = "pointer";
            }

            $tds[i].onmouseout = function() {
                this.parentNode.style.backgroundColor = "white";
            }

            $tds[i].onclick = function() {
                const no = this.parentNode.children[0].innerText;

                // 여기서 새 창을 열도록 수정
                window.open("/Service/detail", "_blank");
            };

        }
    };


    var message = [[${message}]];
    if (message) {
        alert(message);
    }
