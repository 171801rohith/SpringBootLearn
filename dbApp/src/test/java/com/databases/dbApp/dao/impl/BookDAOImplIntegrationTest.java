package com.databases.dbApp.dao.impl;

import com.databases.dbApp.TestDataUtil;
import com.databases.dbApp.dao.AuthorDAO;
import com.databases.dbApp.domain.Author;
import com.databases.dbApp.domain.Book;
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
public class BookDAOImplIntegrationTest {
    private AuthorDAO authorDAO;
    private BookDAOImpl underTest;

    @Autowired
    public BookDAOImplIntegrationTest(BookDAOImpl underTest, AuthorDAO authorDAO) {
        this.underTest = underTest;
        this.authorDAO = authorDAO;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        authorDAO.create(author);
        Book book = TestDataUtil.createBook();
        book.setAuthorId(author.getId());
        underTest.create(book);

        Optional<Book> res = underTest.findOne(book.getIsbn());
        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(book);
    }

    @Test
    public void testThatMultiplesBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        authorDAO.create(author);

        Book book = TestDataUtil.createBook();
        book.setAuthorId(author.getId());
        underTest.create(book);
        Book bookA = TestDataUtil.createBookA();
        bookA.setAuthorId(author.getId());
        underTest.create(bookA);
        Book bookB = TestDataUtil.createBookB();
        bookB.setAuthorId(author.getId());
        underTest.create(bookB);

        List<Book> res = underTest.find();
        assertThat(res).hasSize(5);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Book book = TestDataUtil.createBook();
        underTest.create(book);
        book.setTitle("Goldberg");
        underTest.update(book);
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Book book = TestDataUtil.createBook();
        underTest.create(book);
        underTest.delete(book.getIsbn());
        Optional<Book> result = underTest.findOne(book.getIsbn());

        assertThat(result).isNotPresent();
        assertThat(result).isEmpty();
    }
}
