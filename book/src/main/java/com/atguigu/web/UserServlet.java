package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.test.UserServletTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet  {

    // 接口的引用 = 实现了该接口的类的对象
    //因为 Web层只能和Service层交互, 所以想要调用 UserDao接口的方法,必须通过Service层
    private UserService userService=new UserServiceImpl();



    /**
     *  登录的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //        1.获取请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        //2.调用XXXService.xxx()处理业务
        User loginUser = userService.login(new User(null, username, password, null));

//        3.根据login()返回的结果 判断登录是否成功
        if(loginUser==null ){
            //把错误信息 和 回显的表单项信息 , 保存到Request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);


//             失败
//                跳回登录页面

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }else {
//             成功
//                跳转到登录成功页面 login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     *  注册的方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
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

                //把回显信息 保存到Request域中
                req.setAttribute("msg","用户名已存在!!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);


                System.out.println("用户名["+username+"]已存在!");
//                           跳回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }else{
//                       可用
//                           调用RegistServlet保存到数据库
                userService.registUser(new User(null,username,password,email));
//                           跳转到注册成功页面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);

            }
        }else {

            //把回显信息 保存到Request域中
            req.setAttribute("msg","验证码错误!!");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

//               不正确
            System.out.println("验证码错误["+code+"]错误");

//                   跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }




}
