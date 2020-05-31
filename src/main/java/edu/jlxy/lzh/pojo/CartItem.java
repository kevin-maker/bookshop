package edu.jlxy.lzh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    //商品编号
    private Integer id;
    //商品名称
    private String name;
    //商品数量
    private Integer count;
    //商品单价
    private BigDecimal price;
    //商品总价
    private BigDecimal totalPrice;
}
