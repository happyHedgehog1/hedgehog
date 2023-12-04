const goHome = () => {
    location.href = "../html/index1.html";
};
const goMyPage = () => {
    location.href = "../html/mypageMember.html";
};

/* 이부분에서는 댓글이 없는 경우 display를 none 으로 하고, 아니면 투명한 이미지로 대체한다. */
const titles = document.querySelectorAll(".title_group");
console.log([...titles]
    .map((e) => e.querySelector("img")));
/* 이부분에서는 댓글이 없는 경우 display를 none 으로 하고, 아니면 투명한 이미지로 대체한다. */