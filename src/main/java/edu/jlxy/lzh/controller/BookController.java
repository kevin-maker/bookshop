package edu.jlxy.lzh.controller;

import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.pojo.Page;
import edu.jlxy.lzh.service.BookService;
import edu.jlxy.lzh.service.Impl.BookServiceImpl;
import edu.jlxy.lzh.utils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookController extends BaseController {
    private BookService bookService = new BookServiceImpl();

    /**
     * 添加新图书
     * 
     * @param req
     * @param resp
     * @throws IOException
     */
    public void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        
        DiskFileItemFactory factory = new DiskFileItemFactory();

        ServletFileUpload upload = new ServletFileUpload(factory);

        Map<String,String> map = new HashMap<>();
        try {
            List<FileItem> file = upload.parseRequest(req);

            String fileName = "";
            for (FileItem fileItem : file) {
                if(fileItem.isFormField()){
                    //普通项
                    String key = fileItem.getFieldName();

                    String value = fileItem.getString("UTF-8");

                    map.put(key, value);
                }else{
                    //文件上传

                    fileName = fileItem.getName();
                    
                    InputStream inputStream = fileItem.getInputStream();

                    String path = getServletContext().getRealPath("/static/img");
                    OutputStream outputStream = new FileOutputStream(path+"/"+fileName);
                    int len = 0;
                    byte [] b = new byte[1024];
                    while ((len = inputStream.read(b)) != -1) {
                        outputStream.write(b,0,len);
                    }
                    inputStream.close();
                    outputStream.close();
                }
            }
            Book book = new Book();
            org.apache.commons.beanutils.BeanUtils.populate(book,map);
            book.setImgPath("static/img/" + fileName);
            bookService.addBook(book);
            resp.sendRedirect("/bookshop/manage/book?action=page&pageNo=" + req.getParameter("pageNo")+1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws IOException
     */
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.获取请求的图书编号
        int id = BeanUtils.parseInt(req.getParameter("id"), 0);
        //2.调用service删除
        bookService.deleteBook(id);
        //3.调回图书列表页面
        resp.sendRedirect("/bookshop/manage/book?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 修改图书并保存到数据库
     * @param req
     * @param resp
     */
    public void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.获取请求的参数并封装成对象
        Book book = BeanUtils.beanparams(req.getParameterMap(), new Book());
        //2.调用service修改图书
        bookService.updateBook(book);
        //3.调回图书列表页面
        resp.sendRedirect("/bookshop/manage/book?action=page&pageNo=" + req.getParameter("pageNo"));
    }

    /**
     * 列表页展示图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过service查询全部图书
        List<Book> books = bookService.queryBooks();
        //2.把书保存到request域中
        req.setAttribute("books",books);
        //3.请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    /**
     * 修改图书默认显示被修改的书信息
     * @param req
     * @param resp
     */
    public void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取图书编号
        int id = BeanUtils.parseInt(req.getParameter("id"), 0);
        //2.调用service查询图书
        Book book = bookService.queryBookById(id);
        //3.保存到request域
        req.setAttribute("book",book);
        //4.请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

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
        page.setUrl("manage/book?action=page");
        //3.保存到request域
        req.setAttribute("page",page);
        //4.请求转发
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
