const goMyPage = () => {
    location.href = "../html/mypageMember.html";
};

const agreeCheckImg = (checkImg) => {
    const checkbox = checkImg.nextElementSibling;
    if (checkbox.checked === true) {
        checkbox.checked = false;
        checkImg.src = "../image/regist/check_false.png";
    } else {
        checkbox.checked = true;
        checkImg.src = "../image/regist/check_true.png";
    }
};
const agreeCheckbox = (checkbox) => {
    console.log(checkbox.checked);
    if (checkbox.checked === true) {
        checkbox.previousElementSibling.src = "../image/regist/check_true.png";
    } else {
        checkbox.previousElementSibling.src = "../image/regist/check_false.png";
    }
};

document.getElementById("id_check_button").addEventListener("click", () => {
    const checkText = document.getElementById("check_text");
    checkText.style.display = "inline";
});

const emailAuthentication = document.getElementById("email_authentication");
emailAuthentication.style.display = "none";

let countdownTimer = null;
const startCountdown = () => {
    let timeRemaining = 299;

    countdownTimer = setInterval(() => {
        const minutes = Math.floor(timeRemaining / 60);
        const seconds = timeRemaining % 60;
        const formattedTime = `${String(minutes).padStart(2, "0")}:${String(
            seconds
        ).padStart(2, "0")}`;
        document.querySelector("#reload_timer").textContent = formattedTime;
        if (timeRemaining <= 0) {
            alert("시간초과로 메인으로 돌아갑니다.");
            goHome();
            clearInterval(countdownTimer);
        }
        timeRemaining--;
    }, 1000);
};
const timer = document.getElementById("timer");

const authenticationClick = () => {
    emailAuthentication.style.display = "";
    startCountdown();
    document
        .getElementById("email_button")
        .removeEventListener("click", authenticationClick);
};
document
    .getElementById("email_button")
    .addEventListener("click", authenticationClick);

let count = 0;
document
    .getElementById("email_authentication_button")
    .addEventListener("click", () => {
        const emailCount = document.getElementById("email_count");
        if (count > 3) {
            /* 이부분은 인증에 4번 이상 실패했을 경우 처리 */
        }
        if (true) {
            /* 인증에 성공한 부분 */
            clearInterval(countdownTimer);
            emailAuthentication.style.display = "none";
            emailCount.textContent = "인증이 완료되었습니다.";
        } else {
            /* 인증에 실패할 경우 바로 내보내는건 아니고, 횟수를 추가함.*/
            count++;
            emailCount.textContent = `인증에 실패하였습니다. (시도횟수 ${count}/3)`;
        }
    });
document.getElementById("reload").addEventListener("click", () => {
    clearInterval(countdownTimer);
    document.querySelector("#reload_timer").textContent = "05:00";
    startCountdown();
});
