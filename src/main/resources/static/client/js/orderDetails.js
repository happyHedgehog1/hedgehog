$(document).ready(function () {
    $('#receive_complete').submit(function (e) {
        const isPass = confirm("정말로 배송완료 처리를 하시겠습니까?");
        if(!isPass){
            e.preventDefault();
        }
    })
})