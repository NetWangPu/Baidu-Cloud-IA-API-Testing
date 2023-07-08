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


@WebServlet("/qinggan")
public class QingganServlet extends HttpServlet {
    /**
     * 重写doGet方法
     * doGet 用于处理get请求 一般用于查询 从服务器获取数据
     * @param req  请求对象
     * @param resp 响应对象
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET请求");
        String user_text  = req.getParameter("content");
        System.out.println(user_text);
        // 调用百度AI接口
        AipNlp client = new AipNlp(Const.APP_ID, Const.API_KEY, Const.SECRET_KEY);
        // 调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        JSONObject result = client.sentimentClassify(user_text, options);
        // 输出结果
        System.out.println(result.toString(2)); //2表示格式化输出 4表示每行输出4个字符
        String res = result.toString(2);
        PrintWriter writer = resp.getWriter();
        //IO流：input（读）、output（写）
        writer.write(res);//将分析结果返回前端界面
    }


    /**
     * 重写doPost方法
     * doPost 用于处理post请求 一般用于提交数据到服务器
     * @param req  请求对象
     * @param resp  响应对象
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST请求");
    }
}
