document.addEventListener('DOMContentLoaded', function () {
    // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
    var modifyButtons = document.querySelectorAll('.newMemberModify');
    modifyButtons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            // 기본 동작 중지
            event.preventDefault();

            // 팝업창 열기
            openPopup('/admin/Service/autoMail/modify/newMemberModify.html', 1000, 800);
        });
    });

    // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
    var viewportButtons = document.querySelectorAll('.newMemberViewport');
    viewportButtons.forEach(function (button) {
        button.addEventListener('click', function (event) {
            // 기본 동작 중지
            event.preventDefault();

            // 팝업창 열기
            openPopup('/admin/Service/autoMail/viewport/newMemberViewport.html', 1000, 800);
        });
    });

        // 팝업창 열기 함수
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
    });

    document.addEventListener('DOMContentLoaded', function () {
        // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
        var modifyButtons = document.querySelectorAll('.unregisterModify');
        modifyButtons.forEach(function (button) {
            button.addEventListener('click', function (event) {
                // 기본 동작 중지
                event.preventDefault();
    
                // 팝업창 열기
                openPopup('/admin/Service/autoMail/modify/unregisterModify.html', 1000, 800);
            });
        });
    
        // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
        var viewportButtons = document.querySelectorAll('.unregisterViewport');
        viewportButtons.forEach(function (button) {
            button.addEventListener('click', function (event) {
                // 기본 동작 중지
                event.preventDefault();
    
                // 팝업창 열기
                openPopup('/admin/Service/autoMail/viewport/unregisterViewport.html', 1000, 800);
            });
        });

            // 팝업창 열기 함수
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
});

        
    document.addEventListener('DOMContentLoaded', function () {
        // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
        var modifyButtons = document.querySelectorAll('.passwordModify');
        modifyButtons.forEach(function (button) {
            button.addEventListener('click', function (event) {
                // 기본 동작 중지
                event.preventDefault();
    
                // 팝업창 열기
                openPopup('/admin/Service/autoMail/modify/passwordModify.html', 1000, 800);
            });
        });
    
        // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
        var viewportButtons = document.querySelectorAll('.passwordViewport');
        viewportButtons.forEach(function (button) {
            button.addEventListener('click', function (event) {
                // 기본 동작 중지
                event.preventDefault();
    
                // 팝업창 열기
                openPopup('/admin/Service/autoMail/viewport/passwordViewport.html', 1000, 800);
            });
        });

            // 팝업창 열기 함수
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
});

        document.addEventListener('DOMContentLoaded', function () {
            // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
            var modifyButtons = document.querySelectorAll('.orderListModify');
            modifyButtons.forEach(function (button) {
                button.addEventListener('click', function (event) {
                    // 기본 동작 중지
                    event.preventDefault();
        
                    // 팝업창 열기
                    openPopup('/admin/Service/autoMail/modify/orderListModify.html', 1000, 800);
                });
            });
        
            // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
            var viewportButtons = document.querySelectorAll('.orderListViewport');
            viewportButtons.forEach(function (button) {
                button.addEventListener('click', function (event) {
                    // 기본 동작 중지
                    event.preventDefault();
        
                    // 팝업창 열기
                    openPopup('/admin/Service/autoMail/viewport/orderListViewport.html', 1000, 800);
                });
            });

                // 팝업창 열기 함수
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
});

            document.addEventListener('DOMContentLoaded', function () {
                // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                var modifyButtons = document.querySelectorAll('.dormantMemberModify');
                modifyButtons.forEach(function (button) {
                    button.addEventListener('click', function (event) {
                        // 기본 동작 중지
                        event.preventDefault();
            
                        // 팝업창 열기
                        openPopup('/admin/Service/autoMail/modify/dormantMemberModify.html', 1000, 800);
                    });
                });
            
                // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                var viewportButtons = document.querySelectorAll('.dormantMemberViewport');
                viewportButtons.forEach(function (button) {
                    button.addEventListener('click', function (event) {
                        // 기본 동작 중지
                        event.preventDefault();
            
                        // 팝업창 열기
                        openPopup('/admin/Service/autoMail/viewport/dormantMemberViewport.html', 1000, 800);
                    });
                });

                    // 팝업창 열기 함수
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
});

                
            document.addEventListener('DOMContentLoaded', function () {
                // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                var modifyButtons = document.querySelectorAll('.pointModify');
                modifyButtons.forEach(function (button) {
                    button.addEventListener('click', function (event) {
                        // 기본 동작 중지
                        event.preventDefault();
            
                        // 팝업창 열기
                        openPopup('/admin/Service/autoMail/modify/pointModify.html', 1000, 800);
                    });
                });
            
                // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                var viewportButtons = document.querySelectorAll('.pointViewport');
                viewportButtons.forEach(function (button) {
                    button.addEventListener('click', function (event) {
                        // 기본 동작 중지
                        event.preventDefault();
            
                        // 팝업창 열기
                        openPopup('/admin/Service/autoMail/viewport/pointViewport.html', 1000, 800);
                    });
                });

                    // 팝업창 열기 함수
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
});


                document.addEventListener('DOMContentLoaded', function () {
                    // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                    var modifyButtons = document.querySelectorAll('.mailModify');
                    modifyButtons.forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            // 기본 동작 중지
                            event.preventDefault();
                
                            // 팝업창 열기
                            openPopup('/admin/Service/autoMail/modify/mailModify.html', 1000, 800);
                        });
                    });
                
                    // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                    var viewportButtons = document.querySelectorAll('.mailViewport');
                    viewportButtons.forEach(function (button) {
                        button.addEventListener('click', function (event) {
                            // 기본 동작 중지
                            event.preventDefault();
                
                            // 팝업창 열기
                            openPopup('/admin/Service/autoMail/viewport/mailViewport.html', 1000, 800);
                        });
                    });

                        // 팝업창 열기 함수
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
});

                    document.addEventListener('DOMContentLoaded', function () {
                        // modify 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                        var modifyButtons = document.querySelectorAll('.answerModify');
                        modifyButtons.forEach(function (button) {
                            button.addEventListener('click', function (event) {
                                // 기본 동작 중지
                                event.preventDefault();
                    
                                // 팝업창 열기
                                openPopup('/admin/Service/autoMail/modify/answerModify.html', 1000, 800);
                            });
                        });
                    
                        // viewport 클래스를 가진 요소를 찾아서 클릭 이벤트를 추가
                        var viewportButtons = document.querySelectorAll('.answerViewport');
                        viewportButtons.forEach(function (button) {
                            button.addEventListener('click', function (event) {
                                // 기본 동작 중지
                                event.preventDefault();
                    
                                // 팝업창 열기
                                openPopup('/admin/Service/autoMail/viewport/answerViewport.html', 1000, 800);
                            });
                        });

// 팝업창 열기 함수
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
});

