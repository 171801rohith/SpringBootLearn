package com.databases.dbApp;

import com.databases.dbApp.domain.Author;
import com.databases.dbApp.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createAuthorA() {
        return Author.builder()
                .id(5)
                .name("Roman")
                .age(54)
                .build();
    }

    public static Author createAuthorB() {
        return Author.builder()
                .id(6)
                .name("Roman")
                .age(54)
                .build();
    }

    public static Author createAuthor() {
        return Author.builder()
                .id(9)
                .name("Roman")
                .age(54)
                .build();
    }

    public static Book createBook() {
        return Book.builder()
                .isbn("B67")
                .title("B56")
                .authorId(1)
                .build();
    }
    public static Book createBookA() {
        return Book.builder()
                .isbn("B5")
                .title("B35")
                .authorId(1)
                .build();
    }
    public static Book createBookB() {
        return Book.builder()
                .isbn("B6")
                .title("B5343")
                .authorId(1)
                .build();
    }
}
