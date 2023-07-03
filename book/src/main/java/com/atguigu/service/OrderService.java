package com.atguigu.service;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);//生成订单 = 保存order订单 + 保存orderItem订单项
    public List<Order> showAllOrders();//展示所有订单 -> queryOrders()
    public void sendOrder(String orderId);//发货
    public List<OrderItem> showOrderDetail(String orderId);//管理员或用户 查看订单详情
    public List<Order> showMyOrder(Integer userId);//用户 查看我的订单
    public void receiveOrder(String orderId);//签收
}
