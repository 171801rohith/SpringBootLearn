package com.databases.dbApp.dao.impl;

import com.databases.dbApp.TestDataUtil;
import com.databases.dbApp.domain.Author;
import com.databases.dbApp.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDAOImplTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDAOImpl underTest;

    @Test
    public void testThatCreateBookGeneratesCorrectSql() {
        Book book = TestDataUtil.createBook();

        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)"),
                eq("B1"), eq("B1"), eq(1)
        );

    }

    @Test
    public void testThatFindOneBookGeneratesCorrectSql() {
        underTest.findOne("B1");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ?"),
                ArgumentMatchers.<BookDAOImpl.BookMapper>any(),
                eq("B1")
        );
    }

    @Test
    public void testThatFindManyBookGeneratesCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books"),
                ArgumentMatchers.<BookDAOImpl.BookMapper>any()
        );
    }

    @Test
    public  void testThatFullUpdateBookGeneratesCorrectSql() {
        Book book = TestDataUtil.createBook();
        underTest.update(book);

        verify(jdbcTemplate).update(
                eq("UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?"),
                eq("B67"), eq("B56"), eq(1), eq("B67")
        );
    }
    @Test
    public  void testThatDeleteBookGeneratesCorrectSql() {
        Book book = TestDataUtil.createBook();
        underTest.delete(book.getIsbn());

        verify(jdbcTemplate).update(
                eq("DELETE FROM books WHERE isbn = ?"),
                eq("B67")
        );
    }

}
