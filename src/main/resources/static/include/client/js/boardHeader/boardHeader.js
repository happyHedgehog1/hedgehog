$(document).ready(function () {
    $("#review_board").click(() => {
        localStorage.removeItem('ReviewFormData');
        location.href = '/board/reviewList';
    })
    $("#question_board").click(() => {
        localStorage.removeItem('QuestionFormData');
        location.href = '/board/questionList';
    })
    $("#notice_board").click(() => {
        localStorage.removeItem('noticeFormData');
        location.href = '/board/noticeList';
    })
    $("#faq_board").click(() => {
        localStorage.removeItem('faqFormData');
        location.href = '/board/faqList';
    })
    $("#termsAndCondition_board").click(() => {
        location.href = '/agreement/termsAndConditions';
    })
    $("#privacyPolicy_board").click(() => {
        location.href = '/agreement/privacyPolicy';
    })
})