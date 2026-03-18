package com.databases.dbApp.dao.impl;

import com.databases.dbApp.dao.BookDAO;
import com.databases.dbApp.domain.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAOImpl implements BookDAO {
    private final JdbcTemplate jdbcTemplate;

    public BookDAOImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update(
                "INSERT INTO books (isbn, title, author_id) VALUES (?, ?, ?)",
                book.getIsbn(), book.getTitle(), book.getAuthorId()
        );
    }

    @Override
    public Optional<Book> findOne(String s) {
        List<Book> results = jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books WHERE isbn = ?",
                new BookMapper(), s
        );
        return results.stream().findFirst();
    }

    @Override
    public List<Book> find() {
        return jdbcTemplate.query(
                "SELECT isbn, title, author_id FROM books",
                new BookMapper()
        );
    }

    @Override
    public void update(Book book) {
        jdbcTemplate.update(
                "UPDATE books SET isbn = ?, title = ?, author_id = ? WHERE isbn = ?",
                book.getIsbn(), book.getTitle(), book.getAuthorId(), book.getIsbn()
        );
    }

    @Override
    public void delete(String s) {
        jdbcTemplate.update(
                "DELETE FROM books WHERE isbn = ?", s
        );
    }

    static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .isbn(rs.getString("isbn"))
                    .title(rs.getString("title"))
                    .authorId(rs.getInt("author_id"))
                    .build();
        }
    }
}
