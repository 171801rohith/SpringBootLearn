package com.databases.dbApp.dao.impl;

import com.databases.dbApp.TestDataUtil;
import com.databases.dbApp.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class AuthorDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDAOImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createAuthor();

        underTest.create(author);

        verify(jdbcTemplate).update(
                eq("INSERT INTO authors (id, name, age) VALUES (?, ?, ?)"),
                eq(1), eq("Roman"), eq(54)
        );

    }

    @Test
    public void testThatFindOneAuthorGeneratesCorrectSql() {
        underTest.findOne(1);

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ?"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorMapper>any(),
                eq(1)
        );
    }

    @Test
    public void testThatFindManyAuthorGeneratesCorrectSql() {
        underTest.find();

        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors"),
                ArgumentMatchers.<AuthorDAOImpl.AuthorMapper>any()
        );
    }

    @Test
    public void testThatFullUpdateAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createAuthor();
        underTest.update(author);

        verify(jdbcTemplate).update(
                eq("UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?"),
                eq(9), eq("Roman"), eq(54), eq(9)
        );
    }

    @Test
    public void testThatDeleteAuthorGeneratesCorrectSql() {
        Author author = TestDataUtil.createAuthor();
        underTest.delete(author.getId());

        verify(jdbcTemplate).update(
                eq("DELETE FROM authors WHERE id = ?"),
                eq(9)
        );
    }
}
