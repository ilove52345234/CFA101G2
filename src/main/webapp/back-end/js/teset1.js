window.onload = function(){
    var input = document.getElementById("file_input");
    var result;
    var dataArr = []; // 儲存所選圖片的結果(文件名和base64數據)
    var fd; //FormData方式發送請求
    var oSelect = document.getElementById("select");
    var oAdd = document.getElementById("add");
    var oSubmit = document.getElementById("submit");
    var oInput = document.getElementById("file_input");

    if(typeof FileReader==='undefined'){
        alert("抱歉，你的瀏覽器不支持 FileReader");
        input.setAttribute('disabled','disabled');
    }else{
        input.addEventListener('change',readFile,false);
    }　　　　　//handler


    function readFile(){
        fd = new FormData();
        var iLen = this.files.length;
        for(var i=0;i<iLen;i++){
            // alert(this.files[i].name)
            // input['value']
            if (!this.files[i].name.match(/.jpg|.gif|.png|.jpeg|.bmp/i)){　　//判斷上傳文件格式
                return alert("上傳的圖片格式不正確，請重新選擇");
            }
            var reader = new FileReader();
            fd.append(i,this.files[i]);
            reader.readAsDataURL(this.files[i]); //轉成base64
            reader.fileName = this.files[i].name;

            reader.onload = function(e){
                var imgMsg = {
                    name : this.fileName,//獲取文件名
                    base64 : this.result //reader.readAsDataURL方法執行完後，base64數據儲存在reader.result裡
                }
                dataArr.push(imgMsg);
                result = '<div class="delete">delete</div><div class="result"><img class="subPic" src="'+this.result+'" alt="'+this.fileName+' "/></div>';
                var div = document.createElement('div');
                div.innerHTML = result;
                div['className'] = 'float';
                document.getElementsByTagName('body')[0].appendChild(div); 　　//插入dom樹
                var img = div.getElementsByTagName('img')[0];
                img.onload = function(){
                    var nowHeight = ReSizePic(this); //設置圖片大小
                    this.parentNode.style.display = 'block';
                    var oParent = this.parentNode;
                    if(nowHeight){
                        oParent.style.paddingTop = (oParent.offsetHeight - nowHeight)/2 + 'px';
                    }
                }
                div.onclick = function(){
                    $(this).remove(); // 在頁面中刪除該圖片元素
                }
            }
        }
    }


    function send(){
        var submitArr = [];
        $('.subPic').each(function () {
                submitArr.push({
                    name: $(this).attr('alt'),
                    base64: $(this).attr('src')
                });
            }
        );
        $.ajax({
            url : '/CFA101G2/addRoomServlet',
            type : 'post',
            data : JSON.stringify(submitArr),
            dataType: 'json',
            //processData: false, 用FormData傳fd時需有這兩項
            //contentType: false,
            success : function(data){
                console.log('返回的數據：'+JSON.stringify(data))
            }
        })
    }


    oSelect.onclick=function(){
        oInput.value = ""; // 先將oInput值清空，否則選擇圖片與上次相同時change事件不會觸發
        //清空已選圖片
        $('.float').remove();
        oInput.click();
    }


    oAdd.onclick=function(){
        oInput.value = ""; // 先將oInput值清空，否則選擇圖片與上次相同時change事件不會觸發
        oInput.click();
    }


    oSubmit.onclick=function(){
        if(!dataArr.length){
            return alert('請先選擇文件');
        }
        send();
    }
}
/*
 用ajax發送fd參數時要告訴jQuery不要去處理發送的數據，
 不要去設置Content-Type請求頭才可以發送成功，否則會報“Illegal invocation”的錯誤，
 也就是非法調用，所以要加上“processData: false,contentType: false,”
 * */


function ReSizePic(ThisPic) {
    var RePicWidth = 200; //這裡修改為您想顯示的寬度值
    var TrueWidth = ThisPic.width; //圖片實際寬度
    var TrueHeight = ThisPic.height; //圖片實際高度

    if(TrueWidth>TrueHeight){
        //寬大於高
        var reWidth = RePicWidth;
        ThisPic.width = reWidth;
        //垂直居中
        var nowHeight = TrueHeight * (reWidth/TrueWidth);

        return nowHeight; //將圖片修改後的高度返回，供垂直居中用
    }else{
        //寬小於高
        var reHeight = RePicWidth;
        ThisPic.height = reHeight;
    }
}


