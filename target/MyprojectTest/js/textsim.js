//当界面已经初始化完成，可以与用户交互时，document会自动调用ready方法，
//而ready有一个回调function，系统会自动调用回调函数
$(document).ready(function (en) {
    $("#btn").bind("click", function () {
        var text1 = $("#text1").val();
        var text2 = $("#text2").val();
        var config = {
            url: "textsim?text1=" + text1 + "&text2=" + text2,
            method: "GET",
            dataType:"json",
            data:{},
            success:function (resp){
                console.log("网络请求成功3");
                console.log(resp.data);
                console.log(resp.score);
                var textsim = resp.score;
                $("#textsim").html(textsim);
            },
            error:function (resp){
                console.log(resp);
            }
        };
        $.ajax(config);
    })
})



