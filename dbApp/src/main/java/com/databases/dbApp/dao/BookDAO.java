package com.databases.dbApp.dao;

import com.databases.dbApp.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDAO {
    void create(Book book);

    Optional<Book> findOne(String s);

    List<Book> find();

    void update(Book book);

    void delete(String s);
}
