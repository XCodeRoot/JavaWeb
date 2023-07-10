package com.atguigu.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public  abstract class  BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在获取参数前 , 校准编码
        req.setCharacterEncoding("UTF-8");
        //解决响应的中文乱码
        resp.setContentType("text/html;charset=UTF-8");

        String action=req.getParameter("action");
        //获取action业务 鉴别字符串,获取相应的业务 方法反射对象
        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //这个方法是通过反射 调用业务方法的,即 servlet程序里的方法
            //而 所有的异常都是从Dao层抛到Service层,再抛到Web层里的Servlet方法
            //那Servlet接收到的异常抛到哪里? 没错 就是这个BaseServlet里
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);//因为最终一定是由Filter过滤器来捕获异常,所以在任何一个地方捕获到异常都要向上抛出,绝对不能留着
        }
    }
}
