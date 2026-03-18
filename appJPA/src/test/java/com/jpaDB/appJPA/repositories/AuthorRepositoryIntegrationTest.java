package com.jpaDB.appJPA.repositories;

import com.jpaDB.appJPA.TestDataUtil;
import com.jpaDB.appJPA.domain.Author;
import com.jpaDB.appJPA.repositories.AuthorRepository;
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
public class AuthorRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);
        Author authorA = TestDataUtil.createAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtil.createAuthorB();
        underTest.save(authorB);

        Iterable<Author> result = underTest.findAll();

        assertThat(result).hasSize(3);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);
        author.setName("Goldberg");
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);
        underTest.deleteById(author.getId());
//        underTest.delete(author);
        Optional<Author> result = underTest.findById(author.getId());

        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorAgeLessThan() {
        Author author = TestDataUtil.createAuthor();
        underTest.save(author);
        Author authora = TestDataUtil.createAuthorA();
        underTest.save(authora);
        Author authorb = TestDataUtil.createAuthorB();
        underTest.save(authorb);

        Iterable<Author> result = underTest.findByAgeLessThan(50);

        assertThat(result).contains(author, authora, authorb);

    }
}
