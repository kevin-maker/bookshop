package edu.jlxy.lzh.service;

import edu.jlxy.lzh.pojo.Cart;

public interface OrderService {
    String createOrder(Cart cart,Integer userId);
}