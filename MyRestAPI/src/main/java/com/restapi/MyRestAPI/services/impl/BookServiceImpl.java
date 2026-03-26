package com.restapi.MyRestAPI.services.impl;

import com.restapi.MyRestAPI.domain.dto.BookDTO;
import com.restapi.MyRestAPI.domain.entities.BookEntity;
import com.restapi.MyRestAPI.mappers.Mapper;
import com.restapi.MyRestAPI.repositories.BookRepository;
import com.restapi.MyRestAPI.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private Mapper<BookEntity, BookDTO> bookMapper;

    public BookServiceImpl(BookRepository bookRepository, Mapper<BookEntity, BookDTO> bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO saveBook(String isbn, BookDTO book) {
        BookEntity bookEntity = bookMapper.mapFrom(book);
        bookEntity.setIsbn(isbn);
        return bookMapper.mapTo(bookRepository.save(bookEntity));
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::mapTo)
                .toList();
    }

    @Override
    public Optional<BookDTO> getBookByIsbn(String isbn) {
        Optional<BookEntity> book = bookRepository.findById(isbn);
        return book.map(bookMapper::mapTo);
    }

    @Override
    public void deleteBookByIsbn(String isbn) {
        bookRepository.deleteById(isbn);
    }

    @Override
    public boolean isExists(String isbn) {
        return bookRepository.existsById(isbn);
    }
}
