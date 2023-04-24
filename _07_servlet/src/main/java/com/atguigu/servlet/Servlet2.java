package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取请求的参数(办事的材料)查看
        String username = request.getParameter("username");
        System.out.println("在Servlet2(柜台2)中查看参数(材料)"+username);

//        2.查看柜台1是否有盖章
        Object key1 = request.getAttribute("key1");//getAttribute()查看域对象,键值对
        System.out.println("柜台1是否有章: "+key1);
//        3.处理自己的业务
        System.out.println("Servlet2处理自己的业务");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
