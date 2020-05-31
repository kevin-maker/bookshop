package edu.jlxy.lzh.pojo;

import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {
    @Test
    public void addItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(2,"啦啦啦",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(2,"啦啦啦",1,new BigDecimal(100),new BigDecimal(100)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clean(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(2,"啦啦啦",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clean();
        System.out.println(cart);
    }

    @Test
    public void updateItem(){
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.addItem(new CartItem(2,"啦啦啦",1,new BigDecimal(100),new BigDecimal(100)));
        cart.clean();
        cart.addItem(new CartItem(1,"哈哈哈",1,new BigDecimal(34),new BigDecimal(34)));
        cart.updateCount(1,10);
        System.out.println(cart);
    }
}
