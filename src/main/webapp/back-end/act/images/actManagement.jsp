<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activity Management</title>
</head>
<body>

    <!-- 用fetch API 來取代$.AJAX  傳JSON給 /act/actServlet -->

<!-- 

     


    const username = document.getElementById('username').value;
	const password = document.getElementById('password').value;

        以下開始用Fetch API做async request
        const url = 'member/loginA';
        fetch(url, {
            headers: {
                'content-type': 'application/json'
            },
            method: 'POST',
            body: JSON.stringify({
                username, password
            }),
        })
            .then(function (response) {
                return response.json();
            })
            .then(function (obj) {
                const { message } = obj;
                alert(message);
            });

            第一個then ===> 從 server 傳回response後，從中取得JSON的格式資料  
            https://developer.mozilla.org/zh-TW/docs/Web/API/Fetch_API/Using_Fetch
            第二個then ===> 用promise 的then 來等待前一個then完成後，return的 obj 
            再用ES6的 "解構賦值" 把原本是這樣 ==> const message = obj[message]  
            來寫從obj取出key為message的value
            然後將取得的value assign到「同樣名稱的變數」 const message裡面；

            用了ES6的 "解構賦值"，你就可以寫成這樣 ==>  const { message } = obj;
            來完成跟上面一樣的效果（前提是assign的變數名稱要跟obj的那個keyName是一樣的；

            ES6 6. 解構賦值
                讓JavaScript可以方便的從陣列、物件裡取得內容
            https://blog.typeart.cc/javascript-es6-to-es11-features/

            William：
             .then(function (response) {
                return response.json();	})
            1. 只要是後端回JSON格式的話，基本上都會固定寫這一段

             .then(function (obj) {
                const { message } = obj;
                alert(message);
            });
    ·       2. 這樣obj的位置就會直接拿到JavaScript的物件，裡面裝有後端回應的資料
 -->

    
</body>
</html>