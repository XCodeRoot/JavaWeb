package com.atguigu.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet2 extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        /*  GenericServlet的init方法
            public void init(ServletConfig config) throws ServletException {
                this.config = config;
                this.init();
            }
         */
        //这里重写了init方法,因为父类GenericServlet的init方法是保存config的值,所以要super一下父类 GenericServlet的init方法
        System.out.println("重写了init方法,做了一些工作");


    }

    /** doGet()方法在get请求时调用
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2的doGet()方法");

    }

    /** doPost()方法在get请求时调用
     *
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet2的doPost()方法");
    }
}
