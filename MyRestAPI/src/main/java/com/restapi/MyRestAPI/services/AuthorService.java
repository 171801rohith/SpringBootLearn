package com.restapi.MyRestAPI.services;

import com.restapi.MyRestAPI.domain.dto.AuthorDTO;
import com.restapi.MyRestAPI.domain.dto.PaginatedResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    public AuthorDTO saveAuthor(AuthorDTO author);

    List<AuthorDTO> getAllAuthors();

    PaginatedResponseDTO<AuthorDTO> getAllAuthors(Pageable pageable);

    Optional<AuthorDTO> getAuthorById(Integer id);

    void deleteAuthorById(Integer id);

    boolean isExists(Integer id);

    AuthorDTO partialUpdateAuthorById(Integer id, AuthorDTO authorDTO);
}
