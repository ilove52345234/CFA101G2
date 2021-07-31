$(function () {
    $.get("/CFA101G2/back-end/header.jsp",function (data) {
        $("#header").html(data);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});