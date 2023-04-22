package com.atguigu.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet implements Servlet {

    public HelloServlet() {
        System.out.println("1 构造器");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2 初始化");
//  ServletConfig对象的作用
//        1.可以获取Servlet对象的别名 servlet-name的值
        System.out.println(servletConfig.getServletName());
//        2.获取初始化参数 init-param
        System.out.println(servletConfig.getInitParameter("user"));
        System.out.println(servletConfig.getInitParameter("url"));
//        3.获取ServletContext对象
        System.out.println(servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /** service方法专门用来处理请求和响应数据
     *
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("3  HelloServlet执行了");

        //因为只有ServletRequest的子类HttpServletRequest才有getMethod()方法
        //所以先向下转型,强转
        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
        String method=httpServletRequest.getMethod();

        if("GET".equals(method)){
            doGet();//一个方法只做一件事,降低耦合
        }else if("POST".equals(method)){
            doPost();
        }

    }

    public void doGet(){
        System.out.println("get请求");
        System.out.println("get请求");
        System.out.println("get请求");
    }
    public void doPost(){
        System.out.println("post请求");
        System.out.println("post请求");
        System.out.println("post请求");
    }



     @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4 销毁了");
    }
}
