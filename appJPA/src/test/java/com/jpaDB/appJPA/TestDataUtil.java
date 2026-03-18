package com.jpaDB.appJPA;


import com.jpaDB.appJPA.domain.Author;
import com.jpaDB.appJPA.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createAuthorA() {
        return Author.builder()
                .name("Roman")
                .age(43)
                .build();
    }

    public static Author createAuthorB() {
        return Author.builder()
                .name("Goldberg")
                .age(54)
                .build();
    }

    public static Author createAuthor() {
        return Author.builder()
                .name("Stonegold")
                .age(20)
                .build();
    }

    public static Book createBook(Author author) {
        return Book.builder()
                .isbn("B67")
                .title("B56")
                .author(author)
                .build();
    }
    public static Book createBookA(Author author) {
        return Book.builder()
                .isbn("B5")
                .title("B35")
                .author(author)
                .build();
    }
    public static Book createBookB(Author author) {
        return Book.builder()
                .isbn("B6")
                .title("B5343")
                .author(author)
                .build();
    }
}
