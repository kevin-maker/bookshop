package edu.jlxy.lzh.controller;

import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.pojo.Page;
import edu.jlxy.lzh.service.BookService;
import edu.jlxy.lzh.service.Impl.BookServiceImpl;
import edu.jlxy.lzh.utils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookController extends BaseController{
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理分页
     * @param req
     * @param resp
     */
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取pageNo,pageSize
        int pageNo = BeanUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = BeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用service返回page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("client/book?action=page");
        //3.保存到request域
        req.setAttribute("page",page);
        //4.请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

    /**
     * 搜索
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取pageNo,pageSize,min,max
        int pageNo = BeanUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = BeanUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = BeanUtils.parseInt(req.getParameter("min"), 0);
        int max = BeanUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        //2.调用service返回page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/book?action=pageByPrice");
        //如果有最小请求参数，追加到分页请求地址参数中
        if (req.getParameter("min") != null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        //如果有最大请求参数，追加到分页请求地址参数中
        if (req.getParameter("max") != null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());
        //3.保存到request域
        req.setAttribute("page",page);
        //4.请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
