const goHome = () => {
    /* 위치는 맨 위로 올라가야됨 */
    location.href = "../html/index1.html";
};
const goMyPage = () => {
    /* 위치는 맨 위로 올라가야됨 */
    // 비회원은 주문번호를 통해서 바로 detail 화면으로 들어가서
    // 여기 접속 가능한 고객은
    // 회원뿐이다.
    location.href = "../html/mypageMember.html";
};

const dateButtonContainer = document.getElementById("quick_date_buttons");
const dateStart = document.getElementById("date_start");
const dateEnd = document.getElementById("date_end");

const subMonths = (date, months) => {
    const newDate = new Date(date);
    const month = newDate.getMonth() - months;
    newDate.setMonth(month);
    return newDate;
};

const subWeeks = (date) => {
    const newDate = new Date(date);
    const day = newDate.getDate() - 7;
    newDate.setDate(day);

    return newDate;
};
const formattingDate = (date) => {
    return (
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1).toString().padStart(2, "0") +
        "-" +
        date.getDate().toString().padStart(2, "0")
    );
};

dateButtonContainer.addEventListener("click", (event) => {
    const selectedButton = event.target;
    const currentDate = new Date();
    if (selectedButton.type === "radio" && selectedButton.checked) {
        switch (selectedButton.id) {
            case "date_button_1":
                dateStart.value = formattingDate(currentDate);
                dateEnd.value = formattingDate(currentDate);
                break;
            case "date_button_2":
                dateStart.value = formattingDate(subWeeks(currentDate));
                dateEnd.value = formattingDate(currentDate);
                break;
            case "date_button_3":
                dateStart.value = formattingDate(subMonths(currentDate, 1));
                dateEnd.value = formattingDate(currentDate);
                break;
            case "date_button_4":
                dateStart.value = formattingDate(subMonths(currentDate, 3));
                dateEnd.value = formattingDate(currentDate);
                break;
            case "date_button_5":
                dateStart.value = formattingDate(subMonths(currentDate, 6));
                dateEnd.value = formattingDate(currentDate);
                break;
        }
    }
});

const dateSettingContainer = document.getElementById("date_setting");
const dateSettingsArray = [...dateSettingContainer.children].filter(
    (e) => e.tagName.toLowerCase() === "input"
);
dateSettingsArray[0].value = formattingDate(subMonths(new Date(), 3));
dateSettingsArray[1].value = formattingDate(new Date());

dateSettingsArray.forEach((e) =>
    e.addEventListener("change", () => {
        [...dateButtonContainer.children]
            .filter((e) => e.tagName.toLowerCase() === "input")
            .forEach((e) => (e.checked = false));
    })
);

const submitButtonWrap = document.getElementById("submit_button");
submitButtonWrap.addEventListener("click", () => {
    document.querySelector("#real_submit_button").click();
});

submitButtonWrap.addEventListener("mousedown", () => {
    const [img, input] = submitButtonWrap.children;
    img.src = "../image/searchButton/magnifier_mousedown.png";
    input.style.color = "black";
    submitButtonWrap.style.backgroundColor = 'white';
});
document.addEventListener("mouseup", () => {
    const [img, input] = submitButtonWrap.children;
    img.src = "../image/searchButton/magnifier.png";
    input.style.color = "white";
    submitButtonWrap.style.backgroundColor = '#a7727d';
});

const showDetails = (element)=>{
    /* 임시 */
    console.dir(element);
}