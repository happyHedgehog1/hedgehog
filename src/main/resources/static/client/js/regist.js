/*약관관련 사항 체크버튼 true, false 변경*/
const agreeCheckImg = (checkImg) => {
    const checkbox = checkImg.nextElementSibling;
    if (checkbox.checked === true) {
        checkbox.checked = false;
        checkImg.src = "/client/images/icon/check_false.png";
        checkImg.style.background = '#dcdcdc';
    } else {
        checkbox.checked = true;
        checkImg.src = "/client/images/icon/check_true.png";
        checkImg.style.background = 'white';
    }
};

/*필수 입력을 js로 직접 구현*/
function validate() {
    const contents = document.getElementById('contents');
    const checkbox1 = document.getElementById("agree1");
    const checkbox2 = document.getElementById("agree2");
    if (!checkbox1.checked || !checkbox2.checked) {
        alert("약관에 동의하셔야 회원가입이 가능합니다.")
        contents.focus();
        contents.scrollIntoView({
            behavior: "smooth",
            block: "start",
            inline: "nearest"
        })
        return false;
    }
    const name = document.getElementById("name");
    if (name.value.length <= 0) {
        alert("이름을 입력해주세요.");
        name.focus();
        return false;
    }
    const birthdayInput = document.getElementById("birthday");
    const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
    if (!dateRegex.test(birthdayInput.value)) {
        alert('생년월일을 입력하세요.')
        birthdayInput.focus();
        return false;
    }
    const birthdate = new Date(birthdayInput.value);
    const today = new Date();
    var ageLimitDate = new Date(today.getFullYear() - 14, today.getMonth(), today.getDate());
    if (birthdate > ageLimitDate) {
        alert("만 14세 이상의 사용자만 가입이 가능합니다.");
        birthdayInput.focus();
        return false;
    }
    const gender = document.querySelectorAll("input[name=gender]");
    let isChecked = false;
    for (let i = 0; i < gender.length; i++) {
        if (gender[i].checked) {
            isChecked = true;
            break;
        }
    }
    if (!isChecked) {
        alert("성별을 입력하세요.");
        gender[0].focus();
        return false;
    }
    const userId = document.getElementById("userId");
    if (userId.value.length <= 0) {
        alert("아이디를 입력해주세요.");
        userId.focus();
        return false;
    }
    const password = document.getElementById('userPwd');
    const password2 = document.getElementById("password2")
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])(?!.*\s).{7,13}$/;
    console.log(password)
    if (!passwordRegex.test(password.value)) {
        alert("입력한 비밀번호가 규칙에 위배됩니다. 영문자/숫자/특수문자 각각 1개 이상 입력해야 합니다.")
        password.focus();
        return false;
    }
    if (password2.value !== password.value) {
        alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        password.focus();
        return false;
    }
    const email = document.getElementById("email");
    if (email.value.length <= 0 || document.getElementById("hidden_certified_key").value === "") {
        alert("이메일 인증을 해야합니다. 이메일 인증을 이미완료한 경우 새로고침 해주세요.");
        email.focus();
        return false;
    }
    const emailService = document.querySelectorAll("input[name=emailService]")
    isChecked = false;
    for (let i = 0; i < emailService.length; i++) {
        if (emailService[i].checked) {
            isChecked = true;
            break;
        }
    }
    if (!isChecked) {
        alert("이메일 서비스 유무를 선택하세요.");
        emailService[0].focus();
        return false;
    }
    const phone = document.getElementById("phone");
    if (phone.value.length <= 0) {
        alert('휴대전화 번호를 입력하세요.');
        phone.focus();
        return false;
    }
    const phoneRegex = /^[0-9]{9,11}$/;
    if (!phoneRegex.test(phone.value)) {
        alert('휴대전화 번호는 숫자로만 처리되어야 합니다. 또는 입력한 값이 너무 짧거나 너무 깁니다.');
        phone.focus();
        console.log(phoneRegex);
        console.log(phone);
        console.log(phoneRegex.test(phone));
        return false;
    }
    return true;
}

$(document).ready(() => {
    /*인증번호 입력구역 display:none*/
    const emailAuthentication = document.getElementById("email_authentication");
    emailAuthentication.style.display = "none";

    /*회원가입 버튼 누를 경우. 전송되기전에 필수 입력을 하도록 하는 부분*/
    document.getElementById("regist_button").addEventListener("click", (e) => {
        e.preventDefault();
        let isValidate = validate();
        if (isValidate) {
            document.getElementById("regist").submit();
        }
    })

    /*아이디 체크하는 구역*/
    let regexId = /[a-zA-Z\d]{8,20}$/;
    $('#id_check_button').click(function () {
        $('#id_check_button').css('display', 'none');
        $('#regist_button').css('display', 'none');
        $('#check_text').css('display', 'none');
        $('#userId').prop('readonly', true);
        const userId = $('#userId').val();
        const isValid = regexId.test(userId);
        if (isValid) {
            $.ajax({
                url: "/auth/checkId",
                method: "POST",
                data: {
                    userId: userId
                },
                success: function (result) {
                    if (result.result === "success") { // 아이디가 없으면
                        $('#check_text').css('display', 'block');
                        $('#check_text').text('중복체크 완료')
                        $('#regist_button').css('display', 'block');
                    } else { // 아이디가 있으면
                        $('#id_check_button').css('display', 'inline');
                        $('#check_text').css('display', 'block');
                        $('#check_text').text('아이디가 있습니다.')
                        $('#userId').prop('readonly', false);
                        $('#regist_button').css('display', 'block');
                    }
                },
                error: function (error) {
                    console.log(error);
                    alert('예상치 못한 오류가 발생했습니다.\n메인화면으로 돌아갑니다.');
                    location.href = '/';
                }
            })
        } else {
            $('#id_check_button').css('display', 'inline');
            $('#check_text').css('display', 'block');
            $('#check_text').html('규칙에 맞지 않습니다<br>다시입력해주세요.');
            $('#userId').prop('readonly', false);
            $('#regist_button').css('display', 'block');
        }
    });

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
                location.href = '/auth/regist';
            }
            timeRemaining--;
        }, 1000);
    }

    let regexEmail = /^[a-zA-Z0-9._$+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    $('#email_button').click(function () {
        $('#email_button').css('display', 'none');
        const email = $('#email').val(); // 입력한 email 값
        $('#email').prop('readonly', true);
        const isValid = regexEmail.test(email); // email이 정규식에 맞는가.
        if (isValid) {
            $.ajax({
                url: "/auth/checkEmail",
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
                        $('#email_authentication').css('display', '');
                        $('#hidden_certified_key').val(result.certifiedCode);
                        startCountdown();
                        $('#reload').click(() => {
                            clearInterval(countdownTimer);
                            $('#reload_timer').text("05:00");
                            startCountdown();
                        });
                    } else if (result.result === "sendMiss") {
                        alert("이메일 전송에 실패했습니다.");
                        location.href = "/auth/login";
                    } else {
                        $('#email_button').css('display', '');
                        $('#email').prop('readonly', false);
                        $('#email_message').html('이미 있는 이메일입니다.<br>다시 입력해주세요.');
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
            $('#email').prop('readonly', false);
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
            url: "/auth/emailCertify",
            method: "POST",
            data: {
                inputCertifiedCode: inputCertifiedCode,
                certifiedKey: certifiedKey
            },
            success: function (result) {
                if (result.result === "success") {
                    $("#email_input_wrap").css('display', 'none');
                    $("#email_authentication").css('display', 'none');
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
});