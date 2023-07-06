package com.atguigu.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class ManagerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //将servletRequest 强制转换 成 HttpServletRequest类型
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;
        //因为只有HttpServletRequest 才能 获取到 Session对象
        Object user = httpServletRequest.getSession().getAttribute("user");

        if(user==null){
            httpServletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(request,response);
        }else {
            chain.doFilter(request,response);
        }
    }



    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
