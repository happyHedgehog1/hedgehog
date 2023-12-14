function toggleHamburger() {
    var $hambtn = document.querySelector('.hamburger_button');
    var $sideMenu = document.querySelector('.side_menu');
    $hambtn.classList.toggle('active');
    $sideMenu.classList.toggle('active');
}


function applyHoverEffect(isHover) {
    var targetElement = document.querySelector('.gnb_sub_box');


    if (isHover) {
        targetElement.style.transform = 'translateY(0)';
    } else {
        targetElement.style.transform = 'translateY(-250px)';
    }
}

function deleteBoardFormData() {
    localStorage.removeItem("ReviewFormData");
    localStorage.removeItem("QuestionFormData");
    localStorage.removeItem("NoticeFormData");
    localStorage.removeItem("FaqFormData");
}