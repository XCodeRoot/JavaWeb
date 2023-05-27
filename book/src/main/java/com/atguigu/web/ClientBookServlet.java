package com.atguigu.web;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet{


    BookService bookService=new BookServiceImpl();


    /**
     *  前台分页 功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //1.获取请求参数 pageNo,pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用bookService.page(pageNo,pageSize) :page对象
        Page<Book> page= bookService.page(pageNo,pageSize);

        page.setUrl("client/bookServlet?action=page");


        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到 /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //1.获取请求参数 pageNo,pageSize
        int pageNo= WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min=WebUtils.parseInt(req.getParameter("min"),0);
        int max=WebUtils.parseInt(req.getParameter("max"),Integer.MAX_VALUE);
        //2.调用bookService.page(pageNo,pageSize) :page对象
        Page<Book> page= bookService.pageByPrice(pageNo,pageSize,min,max);


        StringBuilder sb=new StringBuilder("client/bookServlet?action=pageByPrice");
        //如果有最小价格的参数.追加到分页条的地址参数里
        if(req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());


        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到 /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


}
