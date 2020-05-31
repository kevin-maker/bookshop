package edu.jlxy.lzh.mapper;

import edu.jlxy.lzh.mapper.Impl.BookMapperImpl;
import edu.jlxy.lzh.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

public class BookMapperTest {
    private BookMapper bookMapper = new BookMapperImpl();

    @Test
    public void addBook() {
        bookMapper.addBook(new Book(null,"好","191125",new BigDecimal(20),11,89,"static/img/default.jpg"));
    }

    @Test
    public void updateBook() {
        bookMapper.updateBook(new Book(21,"不好","哈哈哈",new BigDecimal(20),1100,0,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookMapper.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        bookMapper.queryBooks().forEach(System.out::println);
    }

    @Test
    public void deleteBook() {
        bookMapper.deleteBook(21);
    }

    @Test
    public void queryTotalCount() {
        System.out.println(bookMapper.queryTotalCount());
    }

    @Test
    public void queryPageDatas() {
        for (Book queryPageData : bookMapper.queryPageDatas(4, 4)) {
            System.out.println(queryPageData);
        }
    }

    @Test
    public void queryTotalCountByPrice() {
        System.out.println(bookMapper.queryTotalCountByPrice(10,50));
    }

    @Test
    public void queryPageDatasByPrice() {
        for (Book queryPageData : bookMapper.queryPageDatasByPrice(0, 4,10,50)) {
            System.out.println(queryPageData);
        }
    }
}
