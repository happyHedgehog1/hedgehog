function redirectQuestionDetail(postCode, searchCondition, searchValue, orderBy, pageNo) {
    const url = '/board/detail?postType=2' +
        '&postCode=' + postCode +
        '&searchCondition=' + encodeURIComponent(searchCondition) +
        '&searchValue=' + encodeURIComponent(searchValue) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}