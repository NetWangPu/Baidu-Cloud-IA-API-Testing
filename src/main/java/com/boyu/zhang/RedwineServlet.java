package com.boyu.zhang;

import com.baidu.aip.imageclassify.AipImageClassify;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 文件大小超过1MB时，将使用磁盘存储临时文件
        maxFileSize = 1024 * 1024 * 5, // 最大文件大小为5MB
        maxRequestSize = 1024 * 1024 * 10 // 最大请求大小为10MB
)
@WebServlet("/RedwineServlet")
public class RedwineServlet extends HttpServlet {
    //百度云货币识别
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        System.out.println(req.getPart("file"));
        //1、接收前端传递过来的图片
        Part part = req.getPart("file");
        //获取图片的输入流
        InputStream inputStream = part.getInputStream();
        //把输入流转换成字节数组
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1){
            outputStream.write(buffer,0,len);
        }
        //把字节数组转换成字节数组
        byte[] data = outputStream.toByteArray();
        System.out.println("图片的大小：" + data.length);
        //2、调用百度AI平台的api
        AipImageClassify client = new AipImageClassify(Const.APP_ID2, Const.API_KEY2, Const.SECRET_KEY2);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        //3、接收百度AI平台的识别结果
        JSONObject jsonObject = client.redwine(data, options);
        System.out.println(jsonObject.toString(2));
        //4、把结果返回给前端页面
        PrintWriter writer = resp.getWriter();
        writer.write(jsonObject.toString(2));
        writer.flush();//刷、清空
        writer.close();//关闭输出流
    }
}