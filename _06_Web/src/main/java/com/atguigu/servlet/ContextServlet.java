package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1. 获取web.xml中配置的上下文参数context-param ,
//        这个参数属于整个工程,可以通过 .getInitParameter("param-name")得到param-value,
        ServletContext context = getServletConfig().getServletContext();
        String s=context.getInitParameter("username");
        System.out.println("context-param参数username的值是: "+ s);
        System.out.println("context-param参数password的值是: "+ context.getInitParameter("password"));

        //得不到,因为调取的是getServletContext(),得到的是上下文参数,而得不到初始化参数
        System.out.println("context-param参数password的值是: "+ context.getInitParameter("url"));


//        2.获取当前工程路径
        System.out.println(context.getContextPath());

//        3.获取工程部署后在服务器 硬盘上的绝对路径(就是电脑里的工程路径)
//        / 斜杠会被服务器解析成 http://ip:port/工程名/
        System.out.println("工程部署的路径: " +context.getRealPath("/"));
        //工程部署的路径: E:\java学习\JavaWeb\_06_Web\target\_06_Web-1.0-SNAPSHOT\
        //tomcat的路径 C:\Users\10455\AppData\Local\JetBrains\IntelliJIdea2021.3\tomcat\99051909-4850-405c-b757-b1ecc1da6ca5
        // 部署的时候就是 把idea里的 webapp这个文件改一个名字, 并把web文件和java字节码映射到tomcat的conf文件里
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
