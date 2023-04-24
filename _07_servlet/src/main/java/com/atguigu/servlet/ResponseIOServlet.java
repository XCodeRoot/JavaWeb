package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置服务器字符集
        response.setCharacterEncoding("UTF-8");
//        通过响应头,设置浏览器字符集
        response.setHeader("Content-Type","text/html;charset=UTF-8   "  );

//        或者用setContentType("text/html;charset=UTF-8") ,可以直接将服务器和浏览器的字符集都设置成UTF-8
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("哈哈哈response's context!!!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
