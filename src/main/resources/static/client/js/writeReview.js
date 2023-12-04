const stars = document.querySelector("#ratings").children;
const emptyStar = document.querySelector("#empty_star").src;
const fullStar = document.querySelector("#full_star").src;

const setRatings = (length) => {
    for (let i = 0; i <= length; i++) {
        stars[i].src = fullStar;
    }
    for (let i = length + 1; i < 5; i++) {
        stars[i].src = emptyStar;
    }
};
