function openPopup(url, width, height) {
    // 팝업창 중앙 정렬을 위한 좌표 계산
    var left = window.innerWidth / 2 - width / 2;
    var top = window.innerHeight / 2 - height / 2;

    // 팝업창 열기
    window.open(
        url,
        '_blank',
        'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left
    );
}
