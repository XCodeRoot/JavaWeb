package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("============doGet()=================================");

//        1.获取参数 (用request.getParameter("name")方法接收在浏览器输入的参数信息username和password,hobby
//        .getParameter("name")这个name就是之前在html页面里设置的 属性name
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");//用String[]数组来接收 多参数的CheckBox标签

        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
        System.out.println("兴趣爱好:"+ Arrays.asList(hobby));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //post请求需要设置请求体的字符集,从而解决乱码问题
        //这个方法要在请求参数之前调用,否则无法生效
        request.setCharacterEncoding("UTF-8");

        System.out.println("==================doPost()===========================================");
//        1.获取参数 (用request.getParameter("name")方法接收在浏览器输入的参数信息username和password,hobby
//        .getParameter("name")这个name就是之前在html页面里设置的 属性name
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobby = request.getParameterValues("hobby");//用String[]数组来接收 多参数的CheckBox标签

        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
        System.out.println("兴趣爱好:"+ Arrays.asList(hobby));


    }
}
