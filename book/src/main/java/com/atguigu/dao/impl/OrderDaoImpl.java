package com.atguigu.dao.impl;

import com.atguigu.dao.OrderDao;
import com.atguigu.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    /** 保存订单(往数据库添加一条订单)
     *
     * @param order
     * @return
     */
    @Override
    public int saveOrder(Order order) {
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`)values(?,?,?,?,?);";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    /** 管理员 查看所有 订单
     *
     * @return
     */
    @Override
    public  List<Order> queryOrders() {
        String sql="select `order_id` as `orderId`,`create_time` as `createTime`,`price` ,`status`,`user_id` as `userId` from t_order;";
        return queryForList(Order.class,sql);
    }

    /** 管理员 改变订单状态 (0 未发货 , 1 已发货 , 2 已签收)
     *
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql="update t_order set status=? where order_id=?";
        return update(sql,status,orderId);
    }

    /** 根据用户编号 查询 用户的订单信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql="select `order_id` as `orderId`,`create_time` as `createTime`,`price` ,`status` from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }
}
