package edu.jlxy.lzh.service;

import edu.jlxy.lzh.pojo.Book;
import edu.jlxy.lzh.service.Impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"你牛逼","hasa",new BigDecimal(100),100,0,"static/img/default.jpg"));
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"不好","哈哈哈",new BigDecimal(20),1100,0,"static/img/default.jpg"));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        bookService.queryBooks().forEach(System.out::println);
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook(22);
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1, 4));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1, 4,10,50));
    }
}
