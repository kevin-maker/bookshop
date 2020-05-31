package edu.jlxy.lzh.mapper;

import edu.jlxy.lzh.pojo.Book;

import java.util.List;

public interface BookMapper {
    /**
     * 添加图书
     * @param book
     * @return
     */
    int addBook(Book book);

    /**
     * 删除图书
     * @param id
     * @return
     */
    int deleteBook(Integer id);

    /**
     * 修改图书
     * @param book
     * @return
     */
    int updateBook(Book book);

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
     * 求总数量
     * @return
     */
    Integer queryTotalCount();

    /**
     * 求当前页数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryPageDatas(int begin, int pageSize);

    /**
     * 按价格查询
     * @param min
     * @param max
     * @return
     */
    Integer queryTotalCountByPrice(int min, int max);

    /**
     * 按价格查询页数据
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    List<Book> queryPageDatasByPrice(int begin, int pageSize, int min, int max);
}
