package com.app.mappers;

import com.app.dtos.responses.AuthorResponse;
import com.app.entities.Author;

import java.util.List;

public class AuthorMapper {

    private AuthorMapper () {}

    public static AuthorResponse toResponse(Author author) {
        return new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName());
    }


    public static AuthorResponse toResponse(String data) {
        String[] authorProperties = data.split(",");
        return new AuthorResponse(Long.parseLong(authorProperties[0]), authorProperties[1],authorProperties[2]);
    }
}
