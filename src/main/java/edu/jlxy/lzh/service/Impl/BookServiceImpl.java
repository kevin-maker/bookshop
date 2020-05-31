package edu.jlxy.lzh.service.Impl;

import edu.jlxy.lzh.mapper.BookMapper;
import edu.jlxy.lzh.mapper.Impl.BookMapperImpl;
import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.pojo.Page;
import edu.jlxy.lzh.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookMapper bookMapper = new BookMapperImpl();
    /**
     * 添加图书
     *
     * @param book
     */
    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    /**
     * 删除图书
     *
     * @param id
     */
    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }

    /**
     * 修改图书
     *
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    /**
     * 根据id查询图书
     *
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
        return bookMapper.queryBookById(id);
    }

    /**
     * 查询所有图书
     *
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        return bookMapper.queryBooks();
    }

    /**
     * 处理分页
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> bookPage = new Page<>();

        //设置每页显示的数量
        bookPage.setPageSize(pageSize);
        //求总记录数
        Integer totalCount = bookMapper.queryTotalCount();
        //设置总记录数
        bookPage.setTotalCount(totalCount);
        //求总页码
        Integer totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0){
            totalPage++;
        }
        //设置总页码
        bookPage.setTotalPage(totalPage);
        //设置当前页码
        bookPage.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> data = bookMapper.queryPageDatas(begin,pageSize);
        //设置当前页数据
        bookPage.setData(data);
        return bookPage;
    }

    /**
     * 搜索
     *
     * @param pageNo
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> bookPage = new Page<>();

        //设置每页显示的数量
        bookPage.setPageSize(pageSize);
        //求总记录数
        Integer totalCount = bookMapper.queryTotalCountByPrice(min,max);
        //设置总记录数
        bookPage.setTotalCount(totalCount);
        //求总页码
        Integer totalPage = totalCount / pageSize;
        if (totalCount % pageSize > 0){
            totalPage++;
        }
        //设置总页码
        bookPage.setTotalPage(totalPage);
        //设置当前页码
        bookPage.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (bookPage.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> data = bookMapper.queryPageDatasByPrice(begin,pageSize,min,max);
        //设置当前页数据
        bookPage.setData(data);
        return bookPage;
    }
}
