package com.restapi.MyRestAPI.services.impl;

import com.restapi.MyRestAPI.domain.dto.AuthorDTO;
import com.restapi.MyRestAPI.domain.entities.AuthorEntity;
import com.restapi.MyRestAPI.mappers.Mapper;
import com.restapi.MyRestAPI.repositories.AuthorRepository;
import com.restapi.MyRestAPI.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private Mapper<AuthorEntity, AuthorDTO> authorMapper;


    public AuthorServiceImpl(AuthorRepository authorRepository, Mapper<AuthorEntity, AuthorDTO> authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;

    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO author) {
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity savedAuthorEntity = authorRepository.save(authorEntity);

        return authorMapper.mapTo(savedAuthorEntity);
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        List<AuthorEntity> authorEntities = authorRepository.findAll();

        return authorEntities.stream()
                .map(authorMapper::mapTo)
                .toList();
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Integer id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        return author.map(authorMapper::mapTo);
    }
}
