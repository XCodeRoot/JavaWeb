package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet{

    private BookService bookService=new BookServiceImpl();



    /** 商品加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数,商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);

        //调用bookService.queryBookById() 得到图书信息
        Book book = bookService.queryBookById(id);

        //把图书信息转换为CartItem商品项
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Cart.addItem(CartItem)添加商品项
        /**
         * 创建 购物车,且只能存在一辆购物车,我们把购物车放进Session里
         */
        Cart cart= (Cart) req.getSession().getAttribute("cart");

        if (cart==null) {
            cart=new Cart();//new一个车
            //创建新的Session
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);//添加商品项到 购物车
        System.out.println(cart);

        //记录最近一次添加到购物车的商品名称
        req.getSession().setAttribute("lastName",cartItem.getName());

        //因为请求头 里有个 Referer 会记录请求发起时,浏览器地址栏的地址 ,我们取出这个地址,直接跳转就行了,这样就不用获取pageNo了!!!
        System.out.println(req.getHeader("Referer"));
        //重定向回 原来商品 所在的页面,省去了传页码参数这个步骤
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /** 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");//获取Session中的购物车对象
        if(cart!=null){
            cart.deleteItem(id);//删除
        }
        //重定向回,商品所在页面
        resp.sendRedirect(req.getHeader("Referer"));
    }


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");//获取Session中的购物车对象
        if(cart!=null){
            cart.clear();
        }
        //重定向回,商品所在页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /** 修改商品数量
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品id
        int id=WebUtils.parseInt(req.getParameter("id"),0);
        //获取商品数量
        int count=WebUtils.parseInt(req.getParameter("count"),1);
        //从Session获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.updateCount(id,count);
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
