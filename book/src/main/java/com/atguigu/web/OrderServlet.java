package com.atguigu.web;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BookServlet{

    private OrderService orderService=new OrderServiceImpl();

    /** 生成订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session中获取 购物车对象cart
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        //从Session中获取 用户 userId
        User loginUser= (User) req.getSession().getAttribute("user");

        if(loginUser==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }

        //保存订单 和 订单号 ,用于页面输出这个订单号
        String orderId=orderService.createOrder(cart,loginUser.getId());
        req.getSession().setAttribute("orderId",orderId);

        //使用 重定向 防止表单重复提交
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

    /** 用户 查看我的订单
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session中获取 用户 userId
        User loginUser= (User) req.getSession().getAttribute("user");
        //保存 orders集合到 req域
        List<Order> orders = orderService.showMyOrder(loginUser.getId());
        req.setAttribute("orders",orders);
        //请求转发
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);
    }
}
