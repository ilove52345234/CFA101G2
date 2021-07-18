//圖片預覽  
$(function () {
    function preview1(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('.img').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#uplImg").change(function (input) {
        preview1(this);
    })
})