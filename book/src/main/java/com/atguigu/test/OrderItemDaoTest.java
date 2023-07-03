package com.atguigu.test;

import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    OrderItemDao orderItemDao =new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDao.saveOrderItem(new OrderItem(
                null,"java从入门到精通",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(
                null,"javaScript从入门到精通",2,new BigDecimal(100),new BigDecimal(200),"1234567890"));
        orderItemDao.saveOrderItem(new OrderItem(
                null,"Netty入门",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));

    }

    @Test
    public void queryOrderItemsByOrderId() {
        for (OrderItem orderItem : orderItemDao.queryOrderItemByOrderId("1234567890")) {
            System.out.println(orderItem);
        }
    }
}