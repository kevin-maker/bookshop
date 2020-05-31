package edu.jlxy.lzh.mapper;

import java.math.BigDecimal;

import org.junit.Test;

import edu.jlxy.lzh.mapper.Impl.OrderItemMapperImpl;
import edu.jlxy.lzh.pojo.OrderItem;

public class OrderItemMapperTest {
    OrderItemMapper orderItemMapper = new OrderItemMapperImpl();
    @Test
    public void saveOrderItem() {
        orderItemMapper.saveOrderItem(new OrderItem(null,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
        orderItemMapper.saveOrderItem(new OrderItem(null,"hahaha",2,new BigDecimal(50),new BigDecimal(100),"1234567890"));
        orderItemMapper.saveOrderItem(new OrderItem(null,"数据结构",1,new BigDecimal(100),new BigDecimal(100),"1234567890"));
    }
}