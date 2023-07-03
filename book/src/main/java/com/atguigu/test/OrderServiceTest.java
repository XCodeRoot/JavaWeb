package com.atguigu.test;

import com.atguigu.pojo.Cart;
import com.atguigu.pojo.CartItem;
import com.atguigu.pojo.Order;
import com.atguigu.pojo.OrderItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    OrderService orderService=new OrderServiceImpl();


    @Test
    public void createOrder() {


        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"javac从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"javac从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));

        System.out.println(orderService.createOrder(cart,1));
    }

    @Test
    public void showAllOrders() {
        for (Order order : orderService.showAllOrders()) {
            System.out.println(order);
        }
    }

    @Test
    public void sendOrder() {
        orderService.sendOrder("16883742094831");
    }

    @Test
    public void showOrderDetail() {
        for (OrderItem orderItem : orderService.showOrderDetail("16883742094831")) {
            System.out.println(orderItem);
        }
    }

    @Test
    public void showMyOrder() {
        for (Order order : orderService.showMyOrder(1)) {
            System.out.println(order);
        }
    }

    @Test
    public void receiveOrder() {
        orderService.receiveOrder("16883742094831");
    }
}