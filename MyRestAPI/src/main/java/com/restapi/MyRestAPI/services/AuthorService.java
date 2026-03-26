package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.dto.AuthorDTO;
import com.restapi.MyRestAPI.domain.entities.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorDTO createAuthor(AuthorDTO author);

    List<AuthorDTO> getAllAuthors();

    Optional<AuthorDTO> getAuthorById(Integer id);

    void deleteAuthorById(Integer id);
}
