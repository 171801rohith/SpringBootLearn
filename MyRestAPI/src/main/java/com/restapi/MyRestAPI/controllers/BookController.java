package com.restapi.MyRestAPI.controllers;

import com.restapi.MyRestAPI.domain.dto.BookDTO;
import com.restapi.MyRestAPI.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> createBook(@PathVariable String isbn, @RequestBody BookDTO bookDTO) {
        if (bookService.isExists(isbn)) return new ResponseEntity<>(bookService.saveBook(isbn, bookDTO), HttpStatus.OK);
        return new ResponseEntity<>(bookService.saveBook(isbn, bookDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/books/all")
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping(path = "/books")
    public ResponseEntity<Page<BookDTO>> getBooksPaginated(Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> getAllBooks(@PathVariable String isbn) {
        Optional<BookDTO> book = bookService.getBookByIsbn(isbn);

        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/books/{isbn}")
    public ResponseEntity<Void> deleteBookByIsbn(@PathVariable String isbn) {
        bookService.deleteBookByIsbn(isbn);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> partialUpdateBookByIsbn(
            @PathVariable String isbn,
            @RequestBody BookDTO bookDTO
    ) {
        if (!bookService.isExists(isbn)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookService.partialUpdateBookByIsbn(isbn, bookDTO));
    }

}
