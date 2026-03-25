package com.restapi.MyRestAPI.controllers;

import com.restapi.MyRestAPI.domain.entities.AuthorEntity;
import com.restapi.MyRestAPI.domain.dto.AuthorDTO;
import com.restapi.MyRestAPI.mappers.Mapper;
import com.restapi.MyRestAPI.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorService.createAuthor(authorDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("id") Integer id) {
        Optional<AuthorDTO> author = authorService.getAuthorById(id);

//        if (author.isPresent()) return ResponseEntity.ok(author.get());
//        else return ResponseEntity.notFound().build();

        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
