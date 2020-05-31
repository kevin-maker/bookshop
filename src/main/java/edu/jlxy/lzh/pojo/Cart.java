package edu.jlxy.lzh.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    //总商品数量
    private Integer totalCount;
    //总金额
    private BigDecimal totalPrice;
    //购物车商品,key是商品编号，value是商品信息
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    /**
     * 添加商品
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        //先查看购物车中是否已添加此商品,如果已添加，数量累加，总金额更新，如果没有添加，直接放入集合
        CartItem item = items.get(cartItem.getId());
        if (item == null){
            items.put(cartItem.getId(),cartItem);
        }else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clean(){
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        //先查看购物车中是否有此商品，如果有，修改商品数量，更新总金额
        CartItem cartItem = items.get(id);
        if (cartItem != null){
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount() {
        totalCount = 0;
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }
}
