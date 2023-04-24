package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("曾到此一游 Response1");

//        //设置响应状态码 : 302表示请求重定向(原网址已搬迁)
//        response.setStatus(302);
//        //设置响应头
//        response.setHeader("Location","http://localhost:8080/_07_servlet/response2");

        //或者 直接用这个方法 ,相当于上面两行
        response.sendRedirect("http://localhost:8080/_07_servlet/response2");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
