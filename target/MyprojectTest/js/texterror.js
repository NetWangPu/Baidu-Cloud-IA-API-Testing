//当界面已经初始化完成，可以与用户交互时，document会自动调用ready方法，
//而ready有一个回调function，系统会自动调用回调函数
$(document).ready(function (en) {
    $("#btn").bind("click", function () {
        var text1 = $("#text").val();
        var config = {
            url: "texterror?text=" + text1,
            method: "GET",
            dataType:"json",
            data:{},
            success:function (resp){
                console.log("网络请求成功4");
                console.log(resp.toString());
                var correct_query = resp.item.correct_query;
                $("#texterror").html(correct_query);
            },
            error:function (resp){
                console.log(resp);
            }
        };
        $.ajax(config);
    })
})



