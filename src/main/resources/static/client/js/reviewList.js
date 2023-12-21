function redirectReviewDetail(postCode, searchCondition, searchValue, orderBy, pageNo) {
    const url = '/board/detail?postType=1' +
        '&postCode=' + postCode +
        '&searchCondition=' + encodeURIComponent(searchCondition) +
        '&searchValue=' + encodeURIComponent(searchValue) +
        '&orderBy=' + encodeURIComponent(orderBy) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}

function paging(currentPage, searchCondition, searchValue, orderBy) {
    const url = '/board/reviewList' +
        '?currentPage=' + currentPage +
        '&searchCondition=' + searchCondition +
        '&searchValue=' + searchValue +
        '&orderBy=' + orderBy;
    window.location.href = url;
}

$(document).ready(function () {
    document.querySelectorAll('.review_img').forEach(function (element) {
        if (element.children.length > 0) {
            // .review_img 아래에 자식이 있는 경우
            const onlyText = element.parentNode.children[element.parentNode.children.length - 1];
            onlyText.style.display = "none";
        }
    });

})

function handleImageError(img) {
    img.style.display = 'none';
}

$(document).ready(function () {
    $('form').submit(function (event) {
        const formData = $(this).serializeArray();
        console.log(formData);
        localStorage.setItem('ReviewFormData', JSON.stringify(formData));
    })
    let storedFormData = localStorage.getItem("ReviewFormData");
    if (storedFormData) {
        storedFormData = JSON.parse(storedFormData);
        console.log(storedFormData);
        $('[name=searchCondition] option[value=' + storedFormData[1].value + ']').prop('selected', true);
        $('[name=searchValue]').val(storedFormData[2].value);
        $('[name=orderBy] option[value=' + storedFormData[3].value + ']').prop('selected', true);
    }
});
$(document).on('click', '#clear_button', function () {
    localStorage.removeItem('ReviewFormData');
})

// function fixImageSize(img) {
//     // 원하는 크기를 지정
//     var maxWidth = 300;
//     var maxHeight = 200;
//
//     // 이미지의 실제 크기를 확인
//     var imgWidth = img.width;
//     var imgHeight = img.height;
//
//     // 이미지 크기가 최대 크기보다 크다면 조정
//     if (imgWidth > maxWidth || imgHeight > maxHeight) {
//         var ratio = Math.min(maxWidth / imgWidth, maxHeight / imgHeight);
//         console.log(ratio);
//         // 새로운 크기로 조정
//         img.width = imgWidth * ratio;
//         img.height = imgHeight * ratio;
//     }
// }