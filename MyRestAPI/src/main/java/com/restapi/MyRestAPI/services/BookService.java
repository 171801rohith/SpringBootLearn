package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.dto.BookDTO;
import com.restapi.MyRestAPI.domain.entities.BookEntity;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookDTO createBook(String isbn, BookDTO book);

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookByIsbn(String isbn);

    void deleteBookByIsbn(String isbn);
}
