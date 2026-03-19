package com.jackson.marshal.controllers;

import com.jackson.marshal.domain.Book;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log
public class BookController {

    @GetMapping(path = "/books")
    public Book retrieveBooks() {
        return Book.builder()
                .isbn("B1")
                .title("Dead End")
                .yearPublished("2026")
                .authorName("Rohith")
                .build();
    }

    @PostMapping(path = "/books")
    public Book createBook(@RequestBody final Book book) {
        log.info("Got Book: " + book.toString());
        return book;
    }
}
