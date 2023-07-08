$(document).ready(function (){
    // Listen to the change event of the file input
    $(".file").bind("change", function (){
        var file = this.files[0];
        $(".prev_view").attr({"src": URL.createObjectURL(file)});
    });

    $(".btn").bind("click", function (){
        // Create a FormData object
        var formData = new FormData();
        // Add the selected file to the FormData object
        formData.append("file", $(".file")[0].files[0]);

        var config = {
            url: "RedwineServlet",
            method: "POST",
            dataType: "json",
            data: formData,
            processData: false,
            contentType: false,
            success: function (resp){
                console.log("成功");
                console.log(resp);
                var name = resp.result.wineNameCn;
                //把要展示的结果 展示到html页面的a标签上
                $("#name").html(name);
            },
            error: function (resp){
                console.log("失败");
                console.log(resp);
            }
        };

        // Execute the AJAX request
        $.ajax(config);
    });
});
