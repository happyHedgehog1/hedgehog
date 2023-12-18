function redirectFaqDetail(postCode, orderBy, pageNo) {
    const url = '/board/detail?postType=4' +
        '&postCode=' + postCode +
        '&orderBy=' + encodeURIComponent(orderBy) +
        '&currentPage=' + pageNo;
    window.location.href = url;
}