package edu.jlxy.lzh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.jlxy.lzh.pojo.Cart;
import edu.jlxy.lzh.pojo.User;
import edu.jlxy.lzh.service.OrderService;
import edu.jlxy.lzh.service.Impl.OrderServiceImpl;

public class OrderController extends BaseController{
    private OrderService orderService = new OrderServiceImpl();
    /**
     * @description: 生成订单
     * @param req 
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取userId
        User loginUser = (User) req.getSession().getAttribute("user");
        if(loginUser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userId = loginUser.getId();
        //调用service中createOrder方法
        String orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("order",orderId);
        //重定向
        resp.sendRedirect("http://localhost:8080/bookshop/pages/cart/checkout.jsp");
    }
}