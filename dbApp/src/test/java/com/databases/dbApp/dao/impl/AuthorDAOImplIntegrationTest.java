package com.databases.dbApp.dao.impl;

import com.databases.dbApp.TestDataUtil;
import com.databases.dbApp.domain.Author;
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
public class AuthorDAOImplIntegrationTest {

    private AuthorDAOImpl underTest;

    @Autowired
    public AuthorDAOImplIntegrationTest(AuthorDAOImpl underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        Author authorA = TestDataUtil.createAuthorA();
        underTest.create(authorA);
        Author authorB = TestDataUtil.createAuthorB();
        underTest.create(authorB);

        List<Author> result = underTest.find();

        assertThat(result).hasSize(5);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        author.setName("Goldberg");
        underTest.update(author);
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.createAuthor();
        underTest.create(author);
        underTest.delete(author.getId());
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isNotPresent();
    }
}
