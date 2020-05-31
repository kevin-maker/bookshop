package edu.jlxy.lzh.service;

import java.math.BigDecimal;

import org.junit.Test;

import edu.jlxy.lzh.pojo.Cart;
import edu.jlxy.lzh.pojo.CartItem;
import edu.jlxy.lzh.service.Impl.OrderServiceImpl;

public class OrderServiceTest {
    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(2,"啦啦啦",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println("订单号是" + orderService.createOrder(cart,6));
    }
}