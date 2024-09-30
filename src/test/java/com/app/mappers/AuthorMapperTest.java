package com.app.mappers;

import com.app.dtos.responses.AuthorResponse;
import com.app.entities.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AuthorMapperTest {

    private final String firstName = "Jake";
    private final String lastName = "Jayc";
    private final Long id = 1L;
    private final AuthorResponse expected = new AuthorResponse(id, firstName, lastName);

    @Test
    void toResponse_Author() {
        Author author = new Author(firstName, lastName);
        author.setId(id);

        AuthorResponse actual = AuthorMapper.toResponse(author);

        assertEquals(expected, actual);
    }

    @Test
    void toResponse_String() {
        String data = String.join(",", id.toString(), firstName, lastName);

        AuthorResponse actual = AuthorMapper.toResponse(data);

        assertEquals(expected, actual);
    }

    @Test
    void toResponse_String_noAuthor() {
        String data = String.join(",", id.toString(), firstName, lastName);

        AuthorResponse actual = AuthorMapper.toResponse(data);

        assertEquals(expected, actual);
    }
}
