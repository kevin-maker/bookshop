package edu.jlxy.lzh.service;

import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.pojo.Page;

import java.util.List;

public interface BookService {

    /**
     * 添加图书
     * @param book
     */
    void addBook(Book book);

    /**
     * 删除图书
     * @param id
     */
    void deleteBook(Integer id);

    /**
     * 修改图书
     * @param book
     */
    void updateBook(Book book);

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    Book queryBookById(Integer id);

    /**
     * 查询所有图书
     * @return
     */
    List<Book> queryBooks();

    /**
     * 处理分页
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page<Book> page(int pageNo, int pageSize);

    /**
     * 搜索
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
