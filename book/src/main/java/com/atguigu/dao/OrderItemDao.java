package com.atguigu.dao;

import com.atguigu.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);//用户 保存订单里的 商品项信息
    public List<OrderItem> queryOrderItemByOrderId(String orderId);//用户或者管理员 根据订单号查询 订单里所有商品项信息
}
