package com.app.repositories;

import com.app.dtos.responses.AuthorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    private final Pageable pageable = Pageable.ofSize(1);

    @Test
    void findByName_response() {
        final Long targetAuthorId = 11L;
        final String targetAuthorFirstName = "Jessica";
        final String targetAuthorLastName = "Thomas";
        final int expectedCount = 1;

        AuthorResponse expected = new AuthorResponse(targetAuthorId, targetAuthorFirstName, targetAuthorLastName);
        List<AuthorResponse> list = authorRepository.findByName(targetAuthorFirstName.toLowerCase(), pageable)
                .getContent();

        assertEquals(expectedCount, list.size());
        AuthorResponse actual = list.get(0);
        assertEquals(expected, actual);
    }

    @Test
    void findAuthors_response() {
        List<AuthorResponse> list = authorRepository.findAuthors(pageable)
                .getContent();

        assertFalse(list.isEmpty());
        AuthorResponse authorResponse = list.get(0);
        assertNotNull(authorResponse.id());
        assertNotNull(authorResponse.firstName());
        assertNotNull(authorResponse.lastName());
    }

}
