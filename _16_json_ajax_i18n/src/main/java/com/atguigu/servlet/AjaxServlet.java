package com.atguigu.servlet;

import com.atguigu.pojo.Person;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends BaseServlet {


    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AJAX请求发送过来了");

        Person person=new Person(1,"国哥");
        //对象 转成json字符串
        Gson gson=new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }

    protected void jQueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryAjax请求发送过来了");

        Person person=new Person(1,"国哥");
        //对象 转成json字符串
        Gson gson=new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }

    protected void jQueryGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryGet请求发送过来了");

        Person person=new Person(1,"国哥");
        //对象 转成json字符串
        Gson gson=new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }

    protected void jQueryPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryPost请求发送过来了");

        Person person=new Person(1,"国哥");
        //对象 转成json字符串
        Gson gson=new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }

    protected void jQueryGetJSON(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQuerySerialize请求发送过来了");

        Person person=new Person(1,"国哥");
        //对象 转成json字符串
        Gson gson=new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }

    protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQuerySerialize请求发送过来了");

        System.out.println("用户名:"+req.getParameter("username"));
        System.out.println("密码"+ req.getParameter("password"));
        Person person=new Person(1,"国哥");
        //对象 转成json字符串
        Gson gson=new Gson();
        String personJsonString = gson.toJson(person);
        resp.getWriter().write(personJsonString);
    }
}
