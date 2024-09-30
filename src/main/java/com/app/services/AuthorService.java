package com.app.services;

import com.app.dtos.responses.AuthorResponse;
import com.app.entities.Author;
import com.app.exceptions.AuthorNotFoundException;
import com.app.mappers.AuthorMapper;
import com.app.repositories.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Page<AuthorResponse> getResponses(Pageable pageable) {
        return authorRepository.findAuthors(pageable);
    }

    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(AuthorNotFoundException::new);
    }

    public AuthorResponse getResponseById(Long id) {
        return AuthorMapper.toResponse(getById(id));
    }

    public Page<AuthorResponse> getResponseByName(String name, Pageable pageable) {
        return authorRepository.findByName(name.toLowerCase(), pageable);
    }
}
