package com.CodeRoot.web;

import com.CodeRoot.pojo.Hirer;
import com.CodeRoot.pojo.Page;
import com.CodeRoot.pojo.Vehicle;
import com.CodeRoot.service.HirerService;
import com.CodeRoot.service.impl.HirerServiceImpl;
import com.CodeRoot.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HirerServlet extends BaseServlet{

    HirerService hirerService=new HirerServiceImpl();




    /** 租赁人信息分页
     *
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
        Page<Hirer> page= hirerService.page(pageNo,pageSize);

        page.setUrl("manager/hirerServlet?action=page");


        //3.保存page对象到Request域中
        req.setAttribute("page",page);
        //4.请求转发到 /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/hirer_manager.jsp").forward(req,resp);
    }
}
