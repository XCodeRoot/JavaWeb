package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistServlet extends HttpServlet {

    // 接口的引用 = 实现了该接口的类的对象
    //因为 Web层只能和Service层交互, 所以想要调用 UserDao接口的方法,必须通过Service层
    private UserService userService=new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//            1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//            2.检查验证码是否正确 ( 先把验证码写死了 : abcde
        if ("abcde".equalsIgnoreCase(code)){// 这个是忽略大小写的 equals 比较
//               正确
//                   3.检查用户名是否可用
            if(userService.existsUsername(username)){
//                       不可用
                System.out.println("用户名["+username+"]已存在!");
//                           跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
            }else{
//                       可用
//                           调用RegistServlet保存到数据库
                userService.registUser(new User(null,username,password,email));
//                           跳转到注册成功页面 regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req,resp);

            }
        }else {
//               不正确
            System.out.println("验证码错误["+code+"]错误");

//                   跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.html").forward(req,resp);
        }




    }
}
