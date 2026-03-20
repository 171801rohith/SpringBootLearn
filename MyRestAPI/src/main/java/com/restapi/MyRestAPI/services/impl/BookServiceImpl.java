package com.restapi.MyRestAPI.services.impl;

import com.restapi.MyRestAPI.domain.entities.BookEntity;
import com.restapi.MyRestAPI.repositories.BookRepository;
import com.restapi.MyRestAPI.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn(isbn);
        return bookRepository.save(bookEntity);
    }
}
