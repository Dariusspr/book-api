package com.app.repositories;

import com.app.dtos.responses.CategoryResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private final Pageable pageable = Pageable.ofSize(1);

    @Test
    void findByTitle_response() {
        final String titleToSearch = "Fiction";
        final int expectedCount = 1;

        List<CategoryResponse> list = categoryRepository.findByTitle(titleToSearch.toLowerCase(), pageable)
                .getContent();

        assertEquals(expectedCount, list.size());
        CategoryResponse actual = list.get(0);
        assertEquals(titleToSearch, actual.title());
        assertNotNull(actual.id());
    }

    @Test
    void findResponses() {
        List<CategoryResponse> list = categoryRepository.findResponses(pageable)
                .getContent();

        assertFalse(list.isEmpty());
        CategoryResponse categoryResponse = list.get(0);
        assertNotNull(categoryResponse.id());
        assertNotNull(categoryResponse.title());
    }
}

