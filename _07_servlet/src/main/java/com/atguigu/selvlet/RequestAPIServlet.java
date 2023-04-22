package com.atguigu.selvlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取请求的资源路径 :  URI=>/_07_servlet/requestAPIServlet
        System.out.println("URI=>"+request.getRequestURI());

//        2.获取请求的统一资源定位符(绝对路径) : URL=>http://localhost:8080/_07_servlet/requestAPIServlet
        System.out.println("URL=>"+request.getRequestURL());

//        3.获取客户端ip地址 : 客户端ip地址=>0:0:0:0:0:0:0:1
        //在IDEA中,使用 localhost访问 时,得到的是 : 客户端ip地址=>0:0:0:0:0:0:0:1
        //在IDEA中,使用 127.0.0.1访问 时,得到的是 : 客户端ip地址=>127.0.0.1
        //在IDEA中,使用 真实ip(10.90.71.184)访问 时,得到的是 : 客户端ip地址=>10.90.71.184
        System.out.println("客户端ip地址=>"+request.getRemoteHost());

//        4.获取请求头
//        浏览器的Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.48
//        打印的  Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 Edg/112.0.1722.48
        System.out.println("User-Agent==>"+request.getHeader("User-Agent"));

//        5.获取请求的方式GET或POST
        System.out.println("请求的方式=>"+request.getMethod());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
