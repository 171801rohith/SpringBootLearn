package com.databases.dbApp.dao.impl;

import com.databases.dbApp.dao.AuthorDAO;
import com.databases.dbApp.domain.Author;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class AuthorDAOImpl implements AuthorDAO {
    private final JdbcTemplate jdbcTemplate;

    public AuthorDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Author author) {
        jdbcTemplate.update(
                "INSERT INTO authors (id, name, age) VALUES (?, ?, ?)",
                author.getId(), author.getName(), author.getAge()
        );
    }

    @Override
    public Optional<Author> findOne(int authorId) {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors WHERE id = ?",
                new AuthorMapper(),
                authorId
        );
        return results.stream().findFirst();
    }

    public List<Author> find() {
        List<Author> results = jdbcTemplate.query(
                "SELECT id, name, age FROM authors",
                new AuthorMapper()
        );
        return results;
    }

    @Override
    public void update(Author author) {
        jdbcTemplate.update(
                "UPDATE authors SET id = ?, name = ?, age = ? WHERE id = ?",
                author.getId(), author.getName(), author.getAge(), author.getId()
        );
    }

    @Override
    public void delete(Integer id) {
        jdbcTemplate.update(
                "DELETE FROM authors WHERE id = ?",
                id
        );
    }

    static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Author.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
}
