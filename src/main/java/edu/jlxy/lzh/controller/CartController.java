package edu.jlxy.lzh.controller;

import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.pojo.Cart;
import edu.jlxy.lzh.pojo.CartItem;
import edu.jlxy.lzh.service.BookService;
import edu.jlxy.lzh.service.Impl.BookServiceImpl;
import edu.jlxy.lzh.utils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartController extends BaseController{
    private BookService bookService = new BookServiceImpl();
    /**
     * 加入购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求参数，商品编号
        int id = BeanUtils.parseInt(req.getParameter("id"), 0);
        //调用service查询图书信息
        Book book = bookService.queryBookById(id);
        //把图书信息转化为CartItem商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用addItem添加商品项
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);
        //将最后一个添加进购物车的商品名称保存到request域中
        req.getSession().setAttribute("lastName",cartItem.getName());
        //重定向回之前的页面
        resp.sendRedirect(req.getHeader("Referer"));
        
    }
    /**
     * 删除购物车商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = BeanUtils.parseInt(req.getParameter("id"),0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null){
            //删除商品
            cart.deleteItem(id);
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 清空购物车商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void cleanItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //清空购物车
            cart.clean();
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求的参数，商品编号，商品数量
        int id = BeanUtils.parseInt(req.getParameter("id"), 0);
        int count = BeanUtils.parseInt(req.getParameter("count"), 1);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart != null){
            //修改商品数量
            cart.updateCount(id, count);
            //重定向回购物车展示页面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
