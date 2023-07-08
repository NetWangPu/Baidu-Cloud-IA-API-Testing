$(document).ready(function (en) {
    //当页面已经准备ok，可以进行交互的时候，会调用ready方法，进而可以通过
    //ready方法的回调方法执行用户所需要的操作

    //分析：当界面可以展示并交互时，需要对按钮进行监听
    $(".btn").bind("click", function () {
        //用户点击按钮以后，需要做哪些操作，将代码写在此处
        //① 获取到用户输入框的内容
        var txt = $(".text").val();
        console.log(txt);//向前端浏览器的控制台输出调试
        //② 携带获取到的用户输入的内容，发送至服务器端（Java代码）
        var config = {
            url: "address?add_txt="+txt,
            method: "GET",
            dataType: "json",
            data: {},
            success: function (resp) {
                console.log("网络请求成功时执行的success回调");
                console.log(resp);
                $(".province").html(resp.province);
                $(".city").html(resp.city);
                $(".county").html(resp.county);
                $(".town").html(resp.town);
            },
            error: function (resp) {
                console.log("网络请求失败时执行的error回调");
            }
        };
        //发起网络的请求
        $.ajax(config);
    });
});