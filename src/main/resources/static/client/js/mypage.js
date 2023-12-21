document.addEventListener("DOMContentLoaded", () => {
    const mypageButtonsContainer = document.getElementById("bottom");
    const mypageButtons = [...mypageButtonsContainer.children];
    mypageButtons.forEach((e) => {
        const img = e.querySelector("img");
        e.addEventListener("mouseover", () => {
            if (img) {
                e.style.backgroundColor = "#A7727D";
                img.src = "/client/images/icon/mypage/" + e.id + "_hovered.png";
            }
        });
        e.addEventListener("mouseout", () => {
            if (img) {
                e.style.backgroundColor = "white";
                img.src = "/client/images/icon/mypage/" + e.id + ".png";
            }
        });
    });
})

function myQuestion() {
    const userId = document.getElementById("id").value;
    const formData = [{
        "name": "currentPage", "value": "1"
    }, {
        "name": "searchCondition", "value": "id"
    }, {
        "name": "searchValue", "value": userId
    }];
    localStorage.setItem('QuestionFormData', JSON.stringify(formData));
    location.href = "/board/questionList?currentPage=1&searchCondition=id&searchValue=" + userId;
}

function myReview() {
    const userId = document.getElementById("id").value;
    const formData = [{
        "name": "currentPage", "value": "1"
    }, {
        "name": "searchCondition", "value": "id"
    }, {
        "name": "searchValue", "value": userId
    }, {
        "name": "orderBy", "value": "gradeDESC"
    }]
    localStorage.setItem('ReviewFormData', JSON.stringify(formData));
    location.href = "/board/reviewList?currentPage=1&searchCondition=id&searchValue=" + userId + "&orderBy=gradeDESC";
}