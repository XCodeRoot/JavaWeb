package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    OrderDao orderDao=new OrderDaoImpl();
    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567890",new Timestamp(new Date().getTime()),new BigDecimal(100),0,1));
    }

    @Test
    public void queryOrders() {
        for (Order queryOrder : orderDao.queryOrders()) {
            System.out.println(queryOrder);
        }
    }

    @Test
    public void changeOrderStatus() {
        orderDao.changeOrderStatus("1234567890",1);
    }

    @Test
    public void queryOrderByUserId() {
        for (Order order : orderDao.queryOrdersByUserId(1)) {
            System.out.println(order);
        }
    }
}