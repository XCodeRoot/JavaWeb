package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {


    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建cookie对象
        Cookie cookie=new Cookie("key1","value1");
        //2.通知客户端 保存cookie对象
        resp.addCookie(cookie);

        resp.getWriter().write("Cookie对象创建成功");
    }
}
