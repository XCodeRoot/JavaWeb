package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {

    // 接口的引用指向 实现了该接口的类的对象
    private BookDao bookDao=new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book
                (null,"国哥为什么那么帅","191125",new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void deleteBookById() {
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book
                (21,"大家都可以这么帅!","国哥",new BigDecimal(9999),1100000,0,null));
    }

    @Test
    public void queryBookById() {
    }

    @Test
    public void queryBooks() {
    }
}