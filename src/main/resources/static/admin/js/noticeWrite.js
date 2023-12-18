$(document).ready(function () {
    $('#summernote').summernote({
        tabsize: 2,
        height: 300,
        toolbar: [
            ['style', ['style']],
            ['font', ['bold', 'underline', 'clear']],
            ['color', ['color']],
            ['para', ['ul', 'ol', 'paragraph']],
            ['table', ['table']],
            ['insert', ['link', 'picture']],
            ['view', ['fullscreen', 'codeview', 'help']]
        ],
        callbacks:{
            onImageUpload : function(files){
                uploadSummernoteImageFile(files[0],this);

            }}
    });
//
// function uploadSummernoteImageFile(file,editor){
//     data = new FormData();
//     data.append("file",file);
//     $.ajax({
//         data:data,
//         type:"POST",
//         url:"/uploadSummernoteImageFile",
//         dataType:"JSON",
//         contentType:false,
//         processData:false,
//         success:function(data){
//             $(editor).summernote("insertImage",data.url);
//             $("#thumbnailPath").append("<option value="+data.url+">"+data.originName+"</option>");
//         }
//
//     });
//
//
// }
//
//
});