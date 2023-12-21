$(document).ready(function () {
    /*이메일 인증번호를 보내는 구역*/
    let countdownTimer = null;

    function startCountdown() {
        let timeRemaining = 299;
        countdownTimer = setInterval(() => {
            const minutes = Math.floor(timeRemaining / 60);
            const seconds = timeRemaining % 60;
            const formattedTime = `${String(minutes).padStart(2, "0")}:${String(seconds).padStart(2, "0")}`;
            document.querySelector("#reload_timer").textContent = formattedTime;
            if (timeRemaining <= -1) {
                alert("시간초과로 새로고침됩니다.");
                clearInterval(countdownTimer);
                location.href = '/auth/searchId';
            }
            timeRemaining--;
        }, 1000);
    }

    let regexEmail = /^[a-zA-Z0-9._$+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    $('#email_button').click(function () {
        $('#email_button').css('display', 'none');
        const email = $('#input_email').val(); // 입력한 email 값
        console.log(email)
        $('#input_email').prop('readonly', true);
        const isValid = regexEmail.test(email); // email이 정규식에 맞는가.
        if (isValid) {
            $.ajax({
                url: "/auth/searchId/checkEmail",
                method: "POST",
                data: {
                    email: email
                },
                success: function (result) {
                    if (result.result === "success") {
                        $('#email_message').html('');
                        $('#email_button').css('display', '');
                        $('#email_button').val('인증완료');
                        $(document).ready(() => $('#email_button').off("click"));
                        $('#authentication_row').css('display', 'table-row');
                        $('#hidden_certified_key').val(result.certifiedCode);
                        startCountdown();
                        $('#reload').click(() => {
                            clearInterval(countdownTimer);
                            $('#reload_timer').text("05:00");
                            startCountdown();
                        });
                    } else if (result.result === "sendMiss") {
                        alert("이메일 전송에 실패했습니다.");
                        location.href = "/auth/searchId";
                    } else {
                        $('#email_button').css('display', '');
                        $('#input_email').prop('readonly', false);
                        $('#email_message').html('이메일에 알맞는 회원이 존재하지 않습니다.<br>다시 입력해주세요.');
                    }
                },
                error: function (error) {
                    console.log(error);
                    alert('예상치 못한 오류가 발생했습니다.\n메인화면으로 돌아갑니다.');
                    location.href = '/';
                }
            })
        } else {
            $('#email_button').css('display', '');
            $('#input_email').prop('readonly', false);
            $('#email_message').html('이메일 규칙에 맞지 않습니다.<br>다시시도해주세요.');
        }
    })

    /*이메일 인증번호를 입력하는 구역*/
    $('#email_authentication_button').click(function () {
        clearInterval(countdownTimer); // 일단 타이머 중지.
        $(document).ready(() => $('#reload').off("click")); // reload 버튼도 중지
        const inputCertifiedCode = $("#email_authentication_number").val();
        const certifiedKey = $('#hidden_certified_key').val();
        $.ajax({
            url: "/auth/searchId/emailCertify",
            method: "POST",
            data: {
                inputCertifiedCode: inputCertifiedCode,
                certifiedKey: certifiedKey
            },
            success: function (result) {
                if (result.result === "success") {
                    $("#email_input_wrap").css('display', 'none');
                    $("#authentication_row").css('display', 'none');
                    $("#email_message").css('font-size', '1.25em');
                    $("#email_message").css('color', 'black');
                    $("#email_message").html('인증이 완료되었습니다.');
                } else {
                    /*실패하면 타이머 다시 작동하기.*/
                    $("#email_message").html('다시 입력해주세요.');
                    startCountdown();
                    $('#reload').click(() => {
                        clearInterval(countdownTimer);
                        $('#reload_timer').text("05:00");
                        startCountdown();
                    });
                }
            },
            error: function (error) {
                console.log(error);
                alert('예상치 못한 오류가 발생했습니다.\n메인화면으로 돌아갑니다.');
                location.href = '/';
            }
        });
    });
})