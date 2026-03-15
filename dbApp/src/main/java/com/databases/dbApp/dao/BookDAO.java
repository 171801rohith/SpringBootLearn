package com.databases.dbApp.dao;

import com.databases.dbApp.domain.Book;

import java.util.Optional;

public interface BookDAO {
    void create(Book book);

    Optional<Book> findOne(String s);
}
