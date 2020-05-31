package edu.jlxy.lzh.mapper.Impl;

import edu.jlxy.lzh.mapper.BaseMapper;
import edu.jlxy.lzh.mapper.OrderMapper;
import edu.jlxy.lzh.pojo.Order;

public class OrderMapperImpl extends BaseMapper implements OrderMapper {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values (?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }
    
}