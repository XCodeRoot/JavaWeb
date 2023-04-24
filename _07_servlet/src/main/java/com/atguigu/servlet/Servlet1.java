package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        1.获取请求的参数(办事的材料)查看
        String username = request.getParameter("username");
        System.out.println("在Servlet1(柜台1)中查看参数(材料)"+username);

//        2.给材料盖一个章,传递到Servlet2(柜台)去查看
        request.setAttribute("key1","柜台1的章");//setAttribute()设置域对象,键值对
//        3.问路 : Servlet2(柜台2)怎么走
        //请求转发 必须要以斜杠打头 / 表示 : http://ip:port/工程名/  ,映射到IDEA的webapp目录里
        //RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");//资源路径
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/form.html");//资源路径
//        4.走向Servlet2(柜台2)
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
