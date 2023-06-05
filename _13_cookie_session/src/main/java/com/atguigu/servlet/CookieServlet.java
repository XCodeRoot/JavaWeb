package com.atguigu.servlet;

import com.atguigu.util.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {


    /**存活一小时的cookie
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void life3600(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie=new Cookie("life3600","life3600");
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
        resp.getWriter().write("存活一小时的cookie");
    }




    /** 退出浏览器,就被删除的cookie(默认 会话级别的cookie)
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void defaultLife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie=new Cookie("defaultLife","defaultLife");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
    }



    /** 立即删除指定cookie
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteNow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.先找到要删除的 cookie
        Cookie cookie=CookieUtils.findCookie("key3",req.getCookies());
        if(cookie!=null){
            //2.调用 setMaxAge(0)
            cookie.setMaxAge(0);
        }
        //3.调用 addCookie()
        resp.addCookie(cookie);

        resp.getWriter().write("key3的cookie已经被删除");
    }


    /** 更新cookie的value
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //1.方案一: 创建 一个新的 同名 Cookie , 覆盖 旧的就行了
//        Cookie cookies=new Cookie("key1","newValue1");
//        resp.addCookie(cookies);
//        resp.getWriter().write("key1的Cookie修改好了");

        //2.先找到需要的Cookie ,再修改
        Cookie cookie=CookieUtils.findCookie("key2",req.getCookies());
        if(cookie!=null){
            cookie.setValue("newValue2");
        }
        //通知客户端保存 cookie
        resp.addCookie(cookie);
    }

    /** 客户端 获取 服务器 Cookie的方法
     *      先获取所有cookie,再遍历查找自己需要的
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();

        for (Cookie cookie : cookies) {
            resp.getWriter().write("Cookie[" + cookie.getName() + "=" + cookie.getValue() + "]<br/>");
        }
        Cookie iWantCookie= CookieUtils.findCookie("key2",cookies);//左参数 为查找目标,右参数为 所有cookie的集合

//        for (Cookie cookie : cookies) {
//            if("key2".equals(cookie.getName())){
//                iWantCookie=cookie;
//                break;
//            }
//        }
        if(iWantCookie!=null){
            resp.getWriter().write("找到了需要的Cookie");
        }

    }

    /** 创建cookie
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createCookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.创建cookie对象
        Cookie cookie1 = new Cookie("key1", "value1");
        //2.通知客户端 保存cookie对象
        resp.addCookie(cookie1);

        //1.创建cookie对象
        Cookie cookie2 = new Cookie("key2", "value2");
        //2.通知客户端 保存cookie对象
        resp.addCookie(cookie2);

        //1.创建cookie对象
        Cookie cookie3 = new Cookie("key3", "value3");
        //2.通知客户端 保存cookie对象
        resp.addCookie(cookie3);
        resp.getWriter().write("Cookie对象创建成功");
    }
}
