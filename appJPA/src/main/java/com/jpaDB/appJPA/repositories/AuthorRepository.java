package com.jpaDB.appJPA.repositories;

import com.jpaDB.appJPA.domain.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Iterable<Author> findByAgeLessThan(int age);
}
