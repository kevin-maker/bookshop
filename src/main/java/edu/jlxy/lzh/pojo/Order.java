package edu.jlxy.lzh.pojo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private String orderId;
    private Date creatTime;
    private BigDecimal price;
    //0表示未发货，1表示已发货，2表示已签收
    private Integer status = 0;
    private Integer userId;
}