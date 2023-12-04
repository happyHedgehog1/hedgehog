const goHome = () => {
    location.href = "../html/index1.html";
};
const authenticationRow = document.getElementById("authentication_row");
authenticationRow.style.display = "none";
const authenticationCompleteRow = document.getElementById(
    "authentication_complete_row"
);
authenticationCompleteRow.style.display = "none";
let countdownTimer = null;
const startCountdown = () => {
    let timeRemaining = 299;

    countdownTimer = setInterval(() => {
        const minutes = Math.floor(timeRemaining / 60);
        const seconds = timeRemaining % 60;
        const formattedTime = `${String(minutes).padStart(2, "0")}:${String(
            seconds
        ).padStart(2, "0")}`;
        document.querySelector("#timer span").textContent = formattedTime;
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
    authenticationRow.style.display = "";
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
    .getElementById("authentication_button")
    .addEventListener("click", () => {
        authenticationCompleteRow.style.display = "";
        if (count > 3) {
            /* 이부분은 인증에 4번 이상 실패했을 경우 처리 */
        }
        if (true) {
            /* 인증에 성공한 부분 */
            clearInterval(countdownTimer);
            authenticationRow.style.display="none";
            authenticationCompleteRow.children[0].textContent =
                "인증에 성공하였습니다.";
        } else {
            /* 인증에 실패할 경우 바로 내보내는건 아니고, 횟수를 추가함.*/
            count++;
            authenticationCompleteRow.children[0].textContent = `인증에 실패하였습니다. (시도횟수 ${count}/3)`;
        }
    });
document.getElementById("reload").addEventListener("click", () => {
    clearInterval(countdownTimer);
    document.querySelector("#timer span").textContent = "05:00";
    startCountdown();
});
