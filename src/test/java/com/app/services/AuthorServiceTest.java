package com.app.services;

import com.app.dtos.responses.AuthorResponse;
import com.app.entities.Author;
import com.app.exceptions.AuthorNotFoundException;
import com.app.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private final Long authorId = 1L;
    private final String authorFirstName = "John";
    private final String authorLastName = "Daise";

    private final  Pageable pageable = Pageable.ofSize(1);
    private AuthorResponse authorResponse = new AuthorResponse(authorId, authorFirstName, authorLastName);


    @Test
    void getResponses() {
        Page<AuthorResponse> expectedPage = new PageImpl<>(List.of(authorResponse));

        when(authorRepository.findAuthors(pageable)).thenReturn(expectedPage);

        Page<AuthorResponse> actualPage = authorService.getResponses(pageable);

        assertEquals(expectedPage, actualPage);
        verify(authorRepository, times(1)).findAuthors(pageable);
    }

    @Test
    void getById_validId() {
        Author author = new Author(authorFirstName, authorLastName);
        author.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        Author actualAuthor = authorService.getById(authorId);

        assertEquals(authorId, actualAuthor.getId());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    void getById_invalidId() {
        Long authorId = 1000L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.getById(authorId));
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    void getResponseById() {
        Author author = new Author(authorFirstName, authorLastName);
        author.setId(authorId);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));

        AuthorResponse actualResponse = authorService.getResponseById(authorId);

        assertEquals(authorId, actualResponse.id());
        verify(authorRepository, times(1)).findById(authorId);
    }

    @Test
    void getResponseByName() {
        Page<AuthorResponse> expectedPage = new PageImpl<>(List.of(authorResponse));

        when(authorRepository.findByName(authorFirstName.toLowerCase(), pageable)).thenReturn(expectedPage);

        Page<AuthorResponse> actualPage = authorService.getResponseByName(authorFirstName, pageable);

        assertEquals(expectedPage, actualPage);
        verify(authorRepository, times(1)).findByName(authorFirstName.toLowerCase(), pageable);
    }
}
