package com.restapi.MyRestAPI.repositories;


import com.restapi.MyRestAPI.TestDataUtil;
import com.restapi.MyRestAPI.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTest {

    private AuthorRepository underTest;

    @Autowired
    public AuthorEntityRepositoryIntegrationTest(AuthorRepository underTest) {
        this.underTest = underTest;

    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);

    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        underTest.save(authorEntity);
        AuthorEntity authorEntityA = TestDataUtil.createAuthorA();
        underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtil.createAuthorB();
        underTest.save(authorEntityB);

        Iterable<AuthorEntity> result = underTest.findAll();

        assertThat(result).hasSize(3);

    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        underTest.save(authorEntity);
        authorEntity.setName("Goldberg");
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        underTest.save(authorEntity);
        underTest.deleteById(authorEntity.getId());
//        underTest.delete(author);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());

        assertThat(result).isNotPresent();
    }

    @Test
    public void testThatGetAuthorAgeLessThan() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        underTest.save(authorEntity);
        AuthorEntity authora = TestDataUtil.createAuthorA();
        underTest.save(authora);
        AuthorEntity authorb = TestDataUtil.createAuthorB();
        underTest.save(authorb);

        Iterable<AuthorEntity> result = underTest.findByAgeLessThan(50);

        assertThat(result).contains(authorEntity, authora);

    }

    @Test
    public void testThatGetAuthorAgeGreaterThan() {
        AuthorEntity authorEntity = TestDataUtil.createAuthor();
        underTest.save(authorEntity);
        AuthorEntity authora = TestDataUtil.createAuthorA();
        underTest.save(authora);
        AuthorEntity authorb = TestDataUtil.createAuthorB();
        underTest.save(authorb);

        Iterable<AuthorEntity> result = underTest.findAuthorsWithAgeGreaterThan(50);

        assertThat(result).containsExactly(authorb);

    }
}