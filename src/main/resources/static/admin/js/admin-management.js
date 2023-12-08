// 관리자 등록 모달 메뉴 열기
document.getElementById("btnAdminAdd").addEventListener("click", function () {
    document.getElementById("myModal").style.display = "flex";
});
// 관리자 등록 모달 닫기
document.getElementById("closeAdminModal").addEventListener("click", function () {
    document.getElementById("myModal").style.display = "none";
});
// 관리자 등록 모달 메뉴 회색화면 부분 눌러도 닫히도록
// document.getElementById("myModal").addEventListener("click", function (event) {
//     if (event.target.id === 'myModal')
//         document.getElementById("myModal").style.display = "none";
// });

// 비밀번호 변경 모달 메뉴 열기
[...document.querySelectorAll('.btnChangePass')].forEach(e => e.addEventListener("click", function () {
    document.getElementById("passChange").style.display = "flex";
}));
// 비밀번호 변경 모달 닫기
document.getElementById("closePassModal").addEventListener("click", function () {
    document.getElementById("passChange").style.display = "none";
});
// 비밀번호 변경 모달 메뉴 회색화면 부분 눌러도 닫히도록
// document.getElementById("passChange").addEventListener("click", function (event) {
//     if (event.target.id === 'passChange')
//         document.getElementById("passChange").style.display = "none";
// });
function deleteAdmin(tag) {
    const userRowChildren = tag.parentNode.parentNode.children;
    const userCode = parseInt(userRowChildren[0].innerText);
    const isDelete = window.confirm(userRowChildren[2].innerText + " 을(를) 삭제하시겠습니까?");
    if (isDelete) {
        $.ajax({
            type: "POST",
            url: "/adminManagement/delete",
            data: {userCode: userCode},
            success: function (response) {
                console.log("삭제 성공:", response);
                location.href="/adminManagement/adminManagement"
            },
            error: function (error) {
                console.log(error);
                alert("예상치 못한 오류가 발생했습니다.\n메인화면으로 돌아갑니다.");
                location.href = '/';
            }
        })
    }else{
        alert("삭제하지 않습니다.");
    }
}
function inputPrimaryKey(tag){
    const userRowChildren = tag.parentNode.parentNode.children;
    $('#userCode').val(userRowChildren[0].innerText);
}