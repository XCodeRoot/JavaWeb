package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet    {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        String password = req.getParameter("password");

        //检查用户名和密码, 如果正确就登录
        if ("wzg168".equals(username)&&"123456".equals(password)){
            //用cookie保存当前用户名
            Cookie cookie = new Cookie("username", "wzg168");
            cookie.setMaxAge(60*60*24*7);//保存7天记录
            resp.addCookie(cookie);
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
}
