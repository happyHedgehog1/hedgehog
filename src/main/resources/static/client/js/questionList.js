function redirectQuestionDetail(postCode, orderBy, pageNo) {
    const url = '/board/detail?postType=2' +
        '&postCode=' + postCode +
        '&orderBy=' + encodeURIComponent(orderBy) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}