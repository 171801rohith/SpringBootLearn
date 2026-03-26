package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookDTO saveBook(String isbn, BookDTO book);

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookByIsbn(String isbn);

    void deleteBookByIsbn(String isbn);

    boolean isExists(String isbn);
}
