package edu.jlxy.lzh.service.Impl;

import java.util.Date;
import java.util.Map;

import edu.jlxy.lzh.mapper.BookMapper;
import edu.jlxy.lzh.mapper.OrderItemMapper;
import edu.jlxy.lzh.mapper.OrderMapper;
import edu.jlxy.lzh.mapper.Impl.BookMapperImpl;
import edu.jlxy.lzh.mapper.Impl.OrderItemMapperImpl;
import edu.jlxy.lzh.mapper.Impl.OrderMapperImpl;
import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.pojo.Cart;
import edu.jlxy.lzh.pojo.CartItem;
import edu.jlxy.lzh.pojo.Order;
import edu.jlxy.lzh.pojo.OrderItem;
import edu.jlxy.lzh.service.OrderService;

public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper = new OrderMapperImpl();
    private OrderItemMapper orderItemMapper = new OrderItemMapperImpl();
    private BookMapper bookMapper = new BookMapperImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单
        orderMapper.saveOrder(order);
        //便利购物车中每一个商品项转换成为订单项保存到数据库
        for(Map.Entry<Integer,CartItem> entry : cart.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CartItem cartItem = entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存到数据库
            orderItemMapper.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookMapper.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookMapper.updateBook(book);
        }
        cart.clean();
        return orderId;
    }
    
}