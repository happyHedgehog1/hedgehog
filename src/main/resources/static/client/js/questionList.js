function redirectQuestionDetail(postCode, searchCondition, searchValue, pageNo) {
    const url = '/board/detail?postType=2' +
        '&postCode=' + postCode +
        '&searchCondition=' + encodeURIComponent(searchCondition) +
        '&searchValue=' + encodeURIComponent(searchValue) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}

function paging(currentPage, searchCondition, searchValue) {
    const url = '/board/questionList' +
        '?currentPage=' + currentPage +
        '&searchCondition=' + searchCondition +
        '&searchValue=' + searchValue;
    window.location.href = url;
}

$(document).ready(function () {
    $('form').submit(function (event) {
        const formData = $(this).serializeArray();
        console.log(formData);
        localStorage.setItem('QuestionFormData', JSON.stringify(formData));
    })
    let storedFormData = localStorage.getItem("QuestionFormData");
    if (storedFormData) {
        storedFormData = JSON.parse(storedFormData);
        console.log(storedFormData);
        $('[name=searchCondition] option[value=' + storedFormData[1].value + ']').prop('selected', true);
        $('[name=searchValue]').val(storedFormData[2].value);
    }
});
$(document).on('click', '#clear_button', function () {
    localStorage.removeItem('QuestionFormData');
})