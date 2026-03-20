package com.restapi.MyRestAPI.repositories;

import com.restapi.MyRestAPI.TestDataUtil;
import com.restapi.MyRestAPI.domain.entities.AuthorEntity;
import com.restapi.MyRestAPI.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTest {
    private AuthorRepository authorRepository;
    private BookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTest(BookRepository underTest, AuthorRepository authorRepository) {
        this.underTest = underTest;
        this.authorRepository = authorRepository;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createBook(authorEntity);
        underTest.save(bookEntity);

        Optional<BookEntity> res = underTest.findById(bookEntity.getTitle());
        assertThat(res).isPresent();
        assertThat(res.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatMultiplesBooksCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        authorRepository.save(authorEntity);

        BookEntity bookEntity = TestDataUtil.createBook(authorEntity);
        underTest.save(bookEntity);
        BookEntity bookEntityA = TestDataUtil.createBookA(authorEntity);
        underTest.save(bookEntityA);
        BookEntity bookEntityB = TestDataUtil.createBookB(authorEntity);
        underTest.save(bookEntityB);

        Iterable<BookEntity> res = underTest.findAll();
        assertThat(res).hasSize(3);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createAuthorB();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createBook(authorEntity);
        underTest.save(bookEntity);
        bookEntity.setTitle("Goldberg");
        underTest.save(bookEntity);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createAuthorB();
        authorRepository.save(authorEntity);
        BookEntity bookEntity = TestDataUtil.createBook(authorEntity);
        underTest.save(bookEntity);
//        underTest.delete(book);
        underTest.deleteById(bookEntity.getIsbn());
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());

        assertThat(result).isNotPresent();
        assertThat(result).isEmpty();
    }
}
