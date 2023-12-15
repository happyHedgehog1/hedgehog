function redirectReviewDetail(postCode, searchCondition, searchValue, pageNo) {
    const url = '/board/detail?postType=1' +
        '&postCode=' + postCode +
        '&searchCondition=' + encodeURIComponent(searchCondition) +
        '&searchValue=' + encodeURIComponent(searchValue) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}