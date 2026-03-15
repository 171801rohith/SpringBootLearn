package com.databases.dbApp.dao;

import com.databases.dbApp.domain.Author;

import java.util.Optional;

public interface AuthorDAO {
    void create(Author author);

    Optional<Author> findOne(int i);
}
