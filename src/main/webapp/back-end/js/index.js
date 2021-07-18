
// 獲取漢堡按鈕

const  burger =document.querySelector(".burger");

const navMenu =document.querySelector(".nav-menu");

const navMenuItems = document.querySelectorAll(".nav-menu li");

burger.addEventListener("click",()=>{
    // 每次點擊增加這個
    burger.classList.toggle("active");
    // console.log(123)

    navMenu.classList.toggle("open");

    navMenuItems.forEach((item,index)=>{
       // 如果有的話去除
        if(item.style.animation){
            item.style.animation = "";
        }else {
            item.style.animation = `0.3s ease-in slideIn forwards ${index * 0.1+0.3}s`;
        }
    });
});


