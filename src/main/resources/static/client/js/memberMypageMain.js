const goHome = () => {
    location.href = "../html/index1.html";
};
const myInfo = () => {
    location.href = "../html/memberInfo.html";
};

const mypageButtonsContainer = document.getElementById("bottom");
const mypageButtons = [...mypageButtonsContainer.children];

mypageButtons.forEach((e) => {
    const img = e.querySelector("img");
    e.addEventListener("mouseover", () => {
        if (img) {
            e.style.backgroundColor = "#A7727D";
            img.src = "../image/mypage/" + e.id + "_hovered.png";
        }
    });
    e.addEventListener("mouseout", () => {
        if (img) {
            e.style.backgroundColor = "white";
            img.src = "../image/mypage/" + e.id + ".png";
        }
    });
});
