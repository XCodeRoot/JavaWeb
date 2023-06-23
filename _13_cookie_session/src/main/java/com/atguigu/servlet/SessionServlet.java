package com.atguigu.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet{

    /** 往session域中保存保存数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //getSession()第一次是创建Session,之后调用都是获取之前创建的Session
        req.getSession().setAttribute("key1","value1");
        resp.getWriter().write("已经往Session域中保存了数据");
    }

    /** 从Session域中获取数据
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute= req.getSession().getAttribute("key1");
        resp.getWriter().write("从Session域中获取到的key1的数据是:"+attribute);
    }

    /** 创建或获取 Session
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrGetSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建和获取Session会话对象
        HttpSession session=req.getSession();
        //判断当前session是否是新创建的
        boolean isNew=session.isNew();
        //获取Session唯一标识id
        String id= session.getId();
        resp.getWriter().write("得到的Session,它的id是:"+id +"<br/>");
        resp.getWriter().write("这个Session是否是新创建的:"+isNew +"<br/>");

    }



    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int maxInactiveInterval = req.getSession().getMaxInactiveInterval();//默认生命周期
        //这里超时时长是1800秒即30分钟 ,因为tomcat的web.xml文件里 有默认配置:
        /*
         <session-config>
            <session-timeout>30</session-timeout>
         </session-config>
         */
        //如果要改的话,直接在idea的xml文件里改就行 ,单独作用于当前xml的话就利用上面的api,getMaxInactiveInterval(int )

        resp.getWriter().write("Session的默认超时时长为"+maxInactiveInterval+ "秒");

    }
    protected void life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.getSession().setMaxInactiveInterval(3);
        resp.getWriter().write("已经设置超时时长为3秒");
    }

    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.getSession().invalidate();
        resp.getWriter().write("已经设置session为无效");
    }

}
