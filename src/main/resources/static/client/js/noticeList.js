function redirectNoticeDetail(postCode, orderBy, pageNo) {
    const url = '/board/detail?postType=3' +
        '&postCode=' + postCode +
        '&orderBy=' + encodeURIComponent(orderBy) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}