$(document).ready(function () {

  $(".qa").click(function(){
    
    $(this).siblings().find(".sub").slideUp(300);
    $(this).find(".sub").stop().slideToggle(200);

  });

});

$(document).ready(function(){

  $(".product_sub_photos a").click(function(){
    let path=$(this).attr("href");
    $(".product_main_photo>img").attr({"src":path}).hide().fadeIn();
    return false;
  
  
  });
});





function openModal() {

  let firstImageSrc = $("#review-img li img:first").attr("src");
  $("#myModal .left-column img").attr("src", firstImageSrc);

  document.getElementById('myModal').style.display = 'block';
  document.getElementById('overlay').style.display = 'flex';


}


document.getElementById("closeModal").addEventListener("click", function() {
  document.getElementById("myModal").style.display = "none";
  document.getElementById('overlay').style.display = 'none';



});


$("#review-img li img").click(function() {
  let chairImageSrc = $(this).attr("src");
  $("#myModal .left-column img").attr("src", chairImageSrc);

});

