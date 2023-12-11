let emailAuthentication = null;
document.addEventListener("DOMContentLoaded", () => {
    emailAuthentication = document.getElementById("email_authentication");
    emailAuthentication.style.display = "none";
});

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("modify_button").addEventListener("click", (e) => {
        e.preventDefault();
        let isValidate = validate();
        if (isValidate) {
            document.getElementById("modify").submit();
        }
    })
})

function validate() {
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
    const passwordRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])(?!.*\s).{7,13}$/;
    console.log(password)
    if (!passwordRegex.test(password.value)) {
        alert("입력한 비밀번호가 규칙에 위배됩니다. 영문자/숫자/특수문자 각각 1개 이상 입력해야 합니다.")
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