package com.restapi.MyRestAPI;

import com.restapi.MyRestAPI.domain.dto.AuthorDTO;
import com.restapi.MyRestAPI.domain.dto.BookDTO;
import com.restapi.MyRestAPI.domain.entities.AuthorEntity;
import com.restapi.MyRestAPI.domain.entities.BookEntity;

import java.awt.print.Book;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static AuthorEntity createAuthorA() {
        return AuthorEntity.builder()
                .name("Roman")
                .age(43)
                .build();
    }

    public static AuthorEntity createAuthorB() {
        return AuthorEntity.builder()
                .name("Goldberg")
                .age(54)
                .build();
    }

    public static AuthorEntity createAuthor() {
        return AuthorEntity.builder()
                .name("Stonegold")
                .age(20)
                .build();
    }

    public static BookEntity createBook(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("B67")
                .title("B56")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createBookA(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("B5")
                .title("B35")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createBookB(AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("B6")
                .title("B5343")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookDTO createBookDTOTest(AuthorDTO authorDTO) {
        return BookDTO.builder()
                .isbn("B67")
                .title("B56")
                .author(authorDTO)
                .build();
    }
}
