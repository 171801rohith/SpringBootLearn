package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public BookDTO saveBook(String isbn, BookDTO book);

    List<BookDTO> getAllBooks();

    Page<BookDTO> getAllBooks(Pageable pageable);

    Optional<BookDTO> getBookByIsbn(String isbn);

    void deleteBookByIsbn(String isbn);

    boolean isExists(String isbn);

    BookDTO partialUpdateBookByIsbn(String isbn, BookDTO bookDTO);
}
