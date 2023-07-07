package com.atguigu.filter;

import com.atguigu.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);//
            JdbcUtils.commitAndClose();//没有异常就提交事务
        } catch (IOException e) {
            JdbcUtils.rollbackAndClose();//有异常就回滚事务
            e.printStackTrace();
            throw new RuntimeException();//把捕获的异常继续抛出 , 给到 tomcat服务器 来统一管理错误
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
