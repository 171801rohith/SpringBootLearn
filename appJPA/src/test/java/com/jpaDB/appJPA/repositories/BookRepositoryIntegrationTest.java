package com.jpaDB.appJPA.repositories;

import com.jpaDB.appJPA.TestDataUtil;
import com.jpaDB.appJPA.domain.Author;
import com.jpaDB.appJPA.domain.Book;
import com.jpaDB.appJPA.repositories.AuthorRepository;
import com.jpaDB.appJPA.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTest {
    private AuthorRepository authorRepository;
    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        authorRepository.save(author);
        Book book = TestDataUtil.createBook(author);
        underTest.save(book);

        Optional<Book> res = underTest.findById(book.getTitle());
        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultiplesBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        authorRepository.save(author);

        Book book = TestDataUtil.createBook(author);
        underTest.save(book);
        Book bookA = TestDataUtil.createBookA(author);
        underTest.save(bookA);
        Book bookB = TestDataUtil.createBookB(author);
        underTest.save(bookB);

        Iterable<Book> res = underTest.findAll();
        assertThat(res).hasSize(3);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.createAuthorB();
        authorRepository.save(author);
        Book book = TestDataUtil.createBook(author);
        underTest.save(book);
        book.setTitle("Goldberg");
        underTest.save(book);
        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.createAuthorB();
        authorRepository.save(author);
        Book book = TestDataUtil.createBook(author);
        underTest.save(book);
//        underTest.delete(book);
        underTest.deleteById(book.getIsbn());
        Optional<Book> result = underTest.findById(book.getIsbn());

        assertThat(result).isNotPresent();
        assertThat(result).isEmpty();
    }
}
