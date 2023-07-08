package com.boyu.zhang;

import com.baidu.aip.nlp.AipNlp;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;


//文本相似度分析
@WebServlet("/textsim")
public class TextsimServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求和返回的编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String text1 = req.getParameter("text1");
        String text2 = req.getParameter("text2");
        AipNlp nlpClient = new AipNlp(Const.APP_ID, Const.API_KEY, Const.SECRET_KEY);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("model", "CNN");
        JSONObject res = nlpClient.simnet(text1, text2, options);
        System.out.println(res.toString(2));
        PrintWriter writer = resp.getWriter();
        writer.write(res.toString(2));
        writer.flush();//刷、清空
        writer.close();//关闭输出流
    }
}