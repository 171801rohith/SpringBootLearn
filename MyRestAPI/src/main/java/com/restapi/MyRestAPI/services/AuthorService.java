package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.dto.AuthorDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorDTO saveAuthor(AuthorDTO author);

    List<AuthorDTO> getAllAuthors();

    Optional<AuthorDTO> getAuthorById(Integer id);

    void deleteAuthorById(Integer id);

    boolean isExists(Integer id);

    AuthorDTO partialUpdateAuthorById(Integer id, AuthorDTO authorDTO);
}
