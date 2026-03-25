package com.restapi.MyRestAPI.controllers;

import com.restapi.MyRestAPI.domain.dto.BookDTO;
import com.restapi.MyRestAPI.domain.entities.BookEntity;
import com.restapi.MyRestAPI.mappers.Mapper;
import com.restapi.MyRestAPI.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.createBook(isbn, bookDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

}
