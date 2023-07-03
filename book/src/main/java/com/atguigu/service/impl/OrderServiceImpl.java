package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService  {

    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao =new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    /** 生成订单 = 保存order订单 + 保存orderItem订单项
     *
     * @param cart
     * @param userId
     * @return 返回orderId ,因为orderId要唯一 ,不返回没人知道
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号 要唯一 ,使用时间戳加用户id 就实现唯一性
        String orderId=System.currentTimeMillis()+""+userId;
        //创建订单对象
        Order order=new Order(orderId,new Timestamp(new Date().getTime()),cart.getTotalPrice(),0,userId);
        //保存订单
        orderDao.saveOrder(order);

        //遍历购物车中每一个商品项,转换成订单项保存到数据库
        for(Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            //取出每一个商品项
            CartItem cartItem=entry.getValue();
            //将商品项转为订单项
            OrderItem orderItem= new OrderItem(
                    null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(), orderId);
            //保存订单项到数据库
            orderItemDao.saveOrderItem(orderItem);

            //更新 图书库存 和 销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);//保存更新
        }

        cart.clear();

        return orderId;
    }

    /** 管理员 查看所有订单
     *
     * @return
     */
    @Override
    public List<Order> showAllOrders() {
       return orderDao.queryOrders();
    }

    /** 发货
     *
     * @param orderId
     */
    @Override
    public void sendOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,1);
    }

    /** 查看订单详情
     *
     * @param orderId
     */
    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
       return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    /** 查看我的订单
     *
     * @param userId
     */
    @Override
    public List<Order> showMyOrder(Integer userId ) {
        return orderDao.queryOrdersByUserId(userId);
    }

    /** 签收
     *
     * @param orderId
     */
    @Override
    public void receiveOrder(String orderId) {
        orderDao.changeOrderStatus(orderId,2);
    }
}
