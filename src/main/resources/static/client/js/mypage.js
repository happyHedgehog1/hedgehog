document.addEventListener("DOMContentLoaded", () => {
        const mypageButtonsContainer = document.getElementById("bottom");
        const mypageButtons = [...mypageButtonsContainer.children];
        mypageButtons.forEach((e) => {
            const img = e.querySelector("img");
            e.addEventListener("mouseover", () => {
                if (img) {
                    e.style.backgroundColor = "#A7727D";
                    img.src = "/client/images/icon/mypage/" + e.id + "_hovered.png";
                }
            });
            e.addEventListener("mouseout", () => {
                if (img) {
                    e.style.backgroundColor = "white";
                    img.src = "/client/images/icon/mypage/" + e.id + ".png";
                }
            });
        });
    }
)


