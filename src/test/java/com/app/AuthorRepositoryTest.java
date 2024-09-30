package com.app;

import com.app.entities.Author;
import com.app.repositories.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class AuthorRepositoryTest {

//    @Autowired
//    private AuthorRepository authorRepository;
//
//    @Test
//    void findByName_upper_exists() {
//    }
}
