package com.app;

import com.app.repositories.AuthorRepository;
import com.app.repositories.BookRepository;
import com.app.repositories.CategoryRepository;
import com.app.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ActiveProfiles("test")
@SpringBootTest
public class DataInitializedTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void dataExists() {
        assertNotEquals(0, authorRepository.count());
        assertNotEquals(0, bookRepository.count());
        assertNotEquals(0, reviewRepository.count());
        assertNotEquals(0, categoryRepository.count());
    }


}
