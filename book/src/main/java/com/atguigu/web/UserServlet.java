package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.test.UserServletTest;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet  {

    // 接口的引用 = 实现了该接口的类的对象
    //因为 Web层只能和Service层交互, 所以想要调用 UserDao接口的方法,必须通过Service层
    private UserService userService=new UserServiceImpl();



    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        //判断 用户名是否重复
        boolean existsUsername = userService.existsUsername(username);
        //把返回的结果封装成Map对象
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        //Map 转成json字符串 (之前学的就是这个
        Gson gson=new Gson();
        String json = gson.toJson(resultMap);
        //响应流中 输出
        resp.getWriter().write(json);
    }

    /**
     *  登录功能
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
            //保存用户登录的信息
            req.getSession().setAttribute("user",loginUser);//这里的loginUser是上面创建过的User对象
//                跳转到登录成功页面 login_success.jsp
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     *  注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取key 为 KAPTCHA_SESSION_KEY常量 的Session的value
        String  token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //立即删除这个Session
        req.getSession().removeAttribute(token);


//            1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");



        User user= WebUtils.copyParamTOBean(req.getParameterMap(),new User());


//            2.检查验证码是否正确 ( 先把验证码写死了 : abcde
        if (token!=null&&token.equalsIgnoreCase(code)){// 这个是忽略大小写的 equals 比较
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


    /** 注销
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注销已有Session
        req.getSession().invalidate();
        //重定向到首页, 因为重定向是两次不同请求,访问两次服务器, 实现更新 数据 刷新页面的作用
        resp.sendRedirect(req.getContextPath());

    }



}
