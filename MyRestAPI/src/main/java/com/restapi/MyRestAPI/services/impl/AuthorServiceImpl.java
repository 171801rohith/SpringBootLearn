package com.restapi.MyRestAPI.services.impl;

import com.restapi.MyRestAPI.domain.dto.AuthorDTO;
import com.restapi.MyRestAPI.domain.dto.PaginatedResponseDTO;
import com.restapi.MyRestAPI.domain.entities.AuthorEntity;
import com.restapi.MyRestAPI.mappers.Mapper;
import com.restapi.MyRestAPI.repositories.AuthorRepository;
import com.restapi.MyRestAPI.repositories.BookRepository;
import com.restapi.MyRestAPI.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private Mapper<AuthorEntity, AuthorDTO> authorMapper;


    public AuthorServiceImpl(AuthorRepository authorRepository, Mapper<AuthorEntity, AuthorDTO> authorMapper, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;

    }

    @Override
    public AuthorDTO saveAuthor(AuthorDTO author) {
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
    public PaginatedResponseDTO<AuthorDTO> getAllAuthors(Pageable pageable) {
        Page<AuthorDTO> page = authorRepository.findAll(pageable).map(authorMapper::mapTo);
        return PaginatedResponseDTO.<AuthorDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    @Override
    public Optional<AuthorDTO> getAuthorById(Integer id) {
        Optional<AuthorEntity> author = authorRepository.findById(id);
        return author.map(authorMapper::mapTo);
    }

    @Override
    public void deleteAuthorById(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean isExists(Integer id) {
        return authorRepository.existsById(id);
    }

    @Override
    public AuthorDTO partialUpdateAuthorById(Integer id, AuthorDTO authorDTO) {
        AuthorEntity authorEntity = authorMapper.mapFrom(authorDTO);
        authorEntity.setId(id);

        AuthorEntity entity = authorRepository.findById(id).map(
                existingAuthor -> {
                    Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);
                    Optional.ofNullable(authorEntity.getAge()).ifPresent(existingAuthor::setAge);
                    return authorRepository.save(existingAuthor);
                }
        ).orElseThrow(() -> new RuntimeException("Author Not exists"));

        return authorMapper.mapTo(entity);
    }
}
