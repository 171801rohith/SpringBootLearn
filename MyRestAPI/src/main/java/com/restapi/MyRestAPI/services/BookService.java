package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.entities.BookEntity;

public interface BookService {
    public BookEntity createBook(String isbn, BookEntity bookEntity);
}
