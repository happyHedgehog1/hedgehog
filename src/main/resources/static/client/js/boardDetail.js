document.addEventListener("DOMContentLoaded", function () {
    $("#delete_btn").on("click", function () {
        deletePost();
    });
    const postType = $('input[name="postType"]').val();
    const postCode = $('input[name="postCode"]').val();

    function deletePost() {
        const userChoice = confirm("정말로 삭제하시겠습니까?");
        if (userChoice) {
            $.ajax({
                type: "POST",
                url: "/board/detail/delete",
                data: {
                    postType: postType,
                    postCode: postCode
                },
                success: function (response) {
                    if (response.result === "guest" || response.result === "fail") {
                        alert("잘못된 접근입니다. 메인으로 돌아갑니다.")
                        window.location.href = '/';
                    } else {
                        alert("삭제에 성공했습니다. 게시판으로 돌아갑니다.")
                        if (postType == 1) {
                            window.location.href = '/board/reviewList';
                        } else if (postType == 2) {
                            window.location.href = '/board/questionList';
                        }
                    }
                },
                error: function (error) {
                    console.error(error);
                }
            })
        } else {
            alert("삭제하지 않습니다. 게시판으로 돌아갑니다.");
            if (postType == 1) {
                window.location.href = '/board/reviewList';
            } else if (postType == 2) {
                window.location.href = '/board/questionList';
            }
        }
    }
})

function product(productCode) {
    location.href = "/productinfo/product/" + productCode;
}