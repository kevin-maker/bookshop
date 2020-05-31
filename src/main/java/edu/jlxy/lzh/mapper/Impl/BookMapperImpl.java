package edu.jlxy.lzh.mapper.Impl;

import edu.jlxy.lzh.mapper.BaseMapper;
import edu.jlxy.lzh.mapper.BookMapper;
import edu.jlxy.lzh.pojo.Book;

import java.util.List;

public class BookMapperImpl extends BaseMapper implements BookMapper {

    /**
     * 添加图书
     *
     * @param book
     * @return
     */
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    /**
     * 删除图书
     *
     * @param id
     * @return
     */
    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id = ?";
        return update(sql,id);
    }

    /**
     * 修改图书
     *
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set `name` = ?,`author` = ?,`price` = ?,`sales` = ?,`stock` = ? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getId());
    }

    /**
     * 根据id查询图书
     *
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(Integer id) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id = ?";
        return queryOne(Book.class,sql,id);
    }

    /**
     * 查询所有图书
     *
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryList(Book.class,sql);
    }

    /**
     * 求总数量
     *
     * @return
     */
    @Override
    public Integer queryTotalCount() {
        String sql = "select count(*) from t_book";
        Number number = (Number) querySingleValue(sql);
        return number.intValue();
    }

    /**
     * 求当前页数据
     *
     * @param begin
     * @param pageSize
     * @return
     */
    @Override
    public List<Book> queryPageDatas(int begin, int pageSize) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book limit ?,?";
        return queryList(Book.class,sql,begin,pageSize);
    }

    /**
     * 按价格查询
     *
     * @param min
     * @param max
     * @return
     */
    @Override
    public Integer queryTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number number = (Number) querySingleValue(sql,min,max);
        return number.intValue();
    }

    /**
     * 按价格查询页数据
     *
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Book> queryPageDatasByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryList(Book.class,sql,min,max,begin,pageSize);
    }
}
