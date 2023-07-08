package com.boyu.zhang;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;



@WebServlet("/address")
public class AddressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和返回的编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        System.out.println("处理地址识别的前端请求");
        //1、获取到前端用户输入的要识别的文字信息内容
        String text = req.getParameter("add_txt");
        System.out.println("用户要识别的地址信息是：" + text);
        //2、调用百度AI平台的地址识别的功能，进行地址信息识别和提取
        AipNlp nlpClient = new AipNlp(Const.APP_ID, Const.API_KEY, Const.SECRET_KEY);
        //3、接收百度AI平台的识别结果
        HashMap<String, Object> options = new HashMap<String, Object>();
        JSONObject result = nlpClient.address(text, options);
        //4、把结果返回给前端页面
        System.out.println(result.toString(2));
        PrintWriter writer = resp.getWriter();
        writer.write(result.toString(2));
        writer.flush();//刷、清空
        writer.close();//关闭输出流
    }
}