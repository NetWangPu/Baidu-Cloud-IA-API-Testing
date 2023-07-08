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


//文本纠错
@WebServlet("/texterror")
public class TexterrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String text = req.getParameter("text");
        AipNlp nlpClient = new AipNlp(Const.APP_ID, Const.API_KEY, Const.SECRET_KEY);
        HashMap<String, Object> options = new HashMap<String, Object>();
        JSONObject res = nlpClient.ecnet(text, options);
        System.out.println(res.toString(2));
        PrintWriter writer = resp.getWriter();
        writer.write(res.toString(2));
        writer.flush();//刷、清空
        writer.close();//关闭输出流
    }
}