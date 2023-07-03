package com.atguigu.dao;

import com.atguigu.pojo.Order;

import java.util.List;

public interface OrderDao {

    public int saveOrder(Order order);//用户 保存订单
    public List<Order> queryOrders();//管理员 查询全部订单
    public int changeOrderStatus(String orderId,Integer status);//管理员 根据订单号 改变订单状态
    public List<Order> queryOrdersByUserId(Integer userId);//根据 用户编号 查询其订单信息
}
