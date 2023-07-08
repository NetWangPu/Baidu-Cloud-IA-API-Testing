//当界面已经初始化完成，可以与用户交互时，document会自动调用ready方法，
//而ready有一个回调function，系统会自动调用回调函数
$(document).ready(function (en) {
    //此处就是回调函数执行的地方
    //根据功能分析，当界面进入ready后，立即对按钮进行监听，监听用户
    //是否点击了"分析"按钮

    //$("选择器名")用于获取到某个控件对象
    //bind方法用于对某个控件执行绑定操作
    //第一个参数click表示执行的点击事件的绑定操作
    //第二个参数function(){}作为一个回调函数，当用户点击了"按钮"时，程序会调用该回调函数
    $("#btn").bind("click", function () {
        //① 获取到用户在输入框中 填写的内容
        var user_input = $("#text").val();
        //③ 把用户输入的内容 通过网络送到 服务端
        var config = {
            url: "qinggan?content=" + user_input,
            method: "GET",
            dataType:"json",
            data:{},
            success:function (resp){
                //当网络请求成功时，会走该success所对应的回调
                console.log("网络请求成功");
                //如果网络请求成功，即成功获取到了功能调用的结果
                //应该把结果数据 更新到前端界面
                //① 首先拿到返回的数据
                console.log(resp.items[0].sentiment);
                var senti = resp.items[0].sentiment;
                if(senti == 0){
                    $("#sentiment").html("负向");
                }else if(senti ==1){
                    $("#sentiment").html("中性");
                }else if(senti ==2){
                    $("#sentiment").html("正向");
                }
                //把其他三个返回值显示到html页面中，展示分析结果
                //先获取到分析结果的各个数据，用变量存起来
                var confidence = resp.items[0].confidence;
                var positve_prob = resp.items[0].positive_prob;
                var negative_prob = resp.items[0].negative_prob;

                //把要展示的结果 展示到html页面的a标签上
                $("#confidence").html(confidence);
                $("#positive").html(positve_prob);
                $("#negative").html(negative_prob);
            },
            error:function (resp){
                //当网络请求失败时，会走error所对应的回调函数
                //当请求失败时，将失败信息输出出来
                console.log(resp);
            }
        };
        //jquery当中使用ajax实现网络请求
        $.ajax(config);
    })
})



