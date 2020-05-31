package edu.jlxy.lzh.mapper.Impl;

import edu.jlxy.lzh.mapper.BaseMapper;
import edu.jlxy.lzh.mapper.OrderItemMapper;
import edu.jlxy.lzh.pojo.OrderItem;

public class OrderItemMapperImpl extends BaseMapper implements OrderItemMapper {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values (?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
    
}