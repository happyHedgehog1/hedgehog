function redirectFaqDetail(postCode, orderBy, pageNo) {
    const url = '/board/detail?postType=4' +
        '&postCode=' + postCode +
        '&orderBy=' + encodeURIComponent(orderBy) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}

function paging(currentPage, orderBy) {
    const url = '/board/faqList' +
        '?currentPage=' + currentPage +
        '&orderBy=' + orderBy;
    window.location.href = url;
}

$(document).ready(function () {
    $('form').submit(function (event) {
        const formData = $(this).serializeArray();
        localStorage.setItem('FaqFormData', JSON.stringify(formData));
    })
    let storedFormData = localStorage.getItem("FaqFormData");
    if (storedFormData) {
        storedFormData = JSON.parse(storedFormData);
        console.log(storedFormData);
        $('[name=orderBy] option[value=' + storedFormData[1].value + ']').prop('selected', true);
    }
});
$(document).on('click', '#clear_button', function () {
    localStorage.removeItem('FaqFormData');
})