package edu.jlxy.lzh.mapper;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;

import edu.jlxy.lzh.mapper.Impl.OrderMapperImpl;
import edu.jlxy.lzh.pojo.Order;

public class OrderMapperTest {
    OrderMapper orderMapper = new OrderMapperImpl();
    @Test
    public void saveOrder() {
        orderMapper.saveOrder(new Order("123456789",new Date(),new BigDecimal(100),0,2));
    }
}