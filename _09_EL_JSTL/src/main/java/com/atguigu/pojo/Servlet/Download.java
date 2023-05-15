package com.atguigu.pojo.Servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Download extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取要下载的文件名
        String downloadFileName="1.png";
//        2.读取要下载的文件内容(通过ServletContext对象可以读取)
        ServletContext servletContext=getServletContext();
        //获取要下载的文件类型
        String mimeType=servletContext.getMimeType("/file/"+downloadFileName);
        System.out.println("要下载的文件类型"+mimeType );


//        4.在回传前,通过响应头,告诉客户端 返回的数据类型
        resp.setContentType(mimeType);



//        5.还要告诉客户端收到的数据是用于使用下载的(还是使用响应头)
        //Context-Disposition响应头,表示收到的数据怎么处理
        //attachment表示附件
        //filename= 表示指定下载的文件名
        resp.setHeader("Content-Disposition","attachment;filename="+downloadFileName);




        /*
            /斜杠 被服务器解析后表示地址为 http://ip:port/工程名/       映射到 代码的webapp目录
         */
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        //获取响应的输出流
        OutputStream outputStream= resp.getOutputStream();
//        3.把下载的文件内容回传给客户端
        //读取流中所有数据 , 复制给输出流
        IOUtils.copy(resourceAsStream,outputStream);



    }
}
