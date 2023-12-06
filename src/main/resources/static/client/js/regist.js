// const checkTrue = document.getElementById("check_true");
// const checkFalse = document.getElementById("check_false");
// console.log(checkTrue)
// console.log(checkFalse)
const agreeCheckImg = (checkImg) => {
    const checkbox = checkImg.nextElementSibling;
    if (checkbox.checked === true) {
        checkbox.checked = false;
        checkImg.src = "/client/images/icon/check_false.png";
    } else {
        checkbox.checked = true;
        checkImg.src = "/client/images/icon/check_true.png";

    }
};
const agreeCheckbox = (checkbox) => {
    console.log(checkbox.checked);
    if (checkbox.checked === true) {
        checkbox.previousElementSibling.src = "/client/images/icon/check_true.png";
    } else {
        checkbox.previousElementSibling.src = "/client/images/icon/check_false.png";
    }
};

let emailAuthentication = null;
document.addEventListener("DOMContentLoaded", () => {
    emailAuthentication = document.getElementById("email_authentication");
    emailAuthentication.style.display = "none";
});