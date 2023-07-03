package com.atguigu.dao.impl;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    /** 保存订单里的 商品项信息 (将订单里的商品项插入进数据库保存起来),1对多的关系
     *
     * @param orderItem
     * @return
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql ="insert into t_order_item(`name`,`count`,`price`,`total_price` ,`order_id` )values(?,?,?,?,?) ";

        return update(sql,
                orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    /** 用户或者管理员 根据订单号查询 订单里所有商品项信息
     *
     * @param orderId
     * @return
     */
    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql="select id,`name`,`count`,`price`,`total_price` as `totalPrice` from t_order_item where order_Id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
