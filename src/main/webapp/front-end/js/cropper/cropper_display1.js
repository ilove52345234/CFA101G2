var i = 0;
    $(function () {



      $("#btnStartShotImage1").click(function () {
        //显示裁剪图片的弹窗
        $('#myModal1').modal('show');
      });

      $("#getPicture1").click(function () {
        $('#myModal1').modal('hide');
      });

      $("#btnStartShotImage1").click(function () {
        if (i == 0) {
          $('#full_image1').css('visibility', 'hidden');
        } 
        i = i + 1;
      });

      $("#inputImage1").click(function () {
        $('#full_image1').css('visibility', 'visible');
      });


    });