package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService=new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        //2.调用XXXService.xxx()处理业务
        User loginUser = userService.login(new User(null, username, password, null));

//        3.根据login()返回的结果 判断登录是否成功
        if(loginUser==null ){
//             失败
//                跳回登录页面

            req.getRequestDispatcher("/pages/user/login.html").forward(req,resp);
        }else {
//             成功
//                跳转到登录成功页面 login_success.html
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req,resp);
        }

    }
}
