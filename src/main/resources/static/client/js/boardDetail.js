// /*
// * location.href='/board/detail/delete'
// * 삭제
// * -----
// * location.href='/board/detail/modify'
// * 수정
// * */
// document.addEventListener("DOMContentLoaded", function () {
//     $("#delete_btn").on("click", function () {
//         deletePost();
//     });
//     $("#modify_btn").on("click", function () {
//         modifyPost();
//     })
//
//     function deletePost() {
//         $.ajax({
//             type: "POST",
//             url: "/board/detail/delete",
//             data: {
//                 userCode: $("input[name='userCode']").val(),
//                 postType: $("input[name='postType']").val(),
//                 postCode: $("input[name='postCode']").val()
//             },
//             success: function (response) {
//                 console.log(response);
//             },
//             error: function (error) {
//                 console.error(error);
//             }
//         })
//     }
// })