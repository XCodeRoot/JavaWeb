package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet{

    //还是 接口的引用执行 实现了该接口的类的对象
    //要和数据库交互,需要访问Dao层,但web层不能直接访问dao层,需要先通过访问service层来访问dao层
    private BookService bookService=new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数 , 封装成 Book 对象
        Book book = WebUtils.copyParamTOBean(req.getParameterMap(), new Book());//调用WebUtils工具类
        //2.调用BookServlet.add()方法 , 保存图书
        bookService.addBook(book);
        //3.请求重定向 到 图书列表 页面  /manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数id,图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用bookService.deleteBookById(); 删除图书
        bookService.deleteBookById(id);
        //3.重定向 回图书管理页面
        //       /manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+ "/manager/bookServlet?action=list");

    }


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数  id = 图书编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //2.调用 bookService.queryBookById() 查询图书
        Book book = bookService.queryBookById(id);
        //3.保存图书到Request域中
        req.setAttribute("book",book);
        //4.请求转发到 /pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数 , 封装成Book对象
        Book book = WebUtils.copyParamTOBean(req.getParameterMap(), new Book());
        //2.调用bookService.updateBook(),将修改好的数据保存到数据库中
        bookService.updateBook(book);
        //3.重定向回 图书管理页面 list那个
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=list");
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过BookService 查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书 保存到Request域
        req.setAttribute("books",books);
        //3.请求转发到 /pages/manager/book_edit.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
