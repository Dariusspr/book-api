package com.app.services;

import com.app.dtos.responses.CategoryResponse;
import com.app.entities.Category;
import com.app.exceptions.CategoryNotFoundException;
import com.app.repositories.CategoryRepository;
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
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private final Pageable pageable = Pageable.ofSize(1);

    private final Long categoryId = 1L;
    private final String categoryTitle = "Fiction";
    private final CategoryResponse categoryResponse = new CategoryResponse(categoryId, categoryTitle);

    @Test
    void getResponses() {
        Page<CategoryResponse> expectedPage = new PageImpl<>(List.of(categoryResponse));

        when(categoryRepository.findResponses(pageable)).thenReturn(expectedPage);

        Page<CategoryResponse> actualPage = categoryService.getResponses(pageable);

        assertEquals(expectedPage, actualPage);
        verify(categoryRepository, times(1)).findResponses(pageable);
    }

    @Test
    void getById_validId() {
        Category category = new Category(categoryTitle);
        category.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        Category actualCategory = categoryService.getById(categoryId);

        assertEquals(categoryId, actualCategory.getId());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void getById_invalidId() {
        Long categoryId = 1000L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.getById(categoryId));
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void getResponseById() {
        Category category = new Category();
        category.setId(categoryId);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        CategoryResponse actualResponse = categoryService.getResponseById(categoryId);

        assertEquals(categoryId, actualResponse.id());
        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    void getResponsesByTitle() {
        Page<CategoryResponse> expectedPage = new PageImpl<>(List.of(categoryResponse));

        when(categoryRepository.findByTitle(categoryTitle.toLowerCase(), pageable)).thenReturn(expectedPage);

        Page<CategoryResponse> actualPage = categoryService.getResponsesByTitle(categoryTitle, pageable);

        assertEquals(expectedPage, actualPage);
        verify(categoryRepository, times(1)).findByTitle(categoryTitle.toLowerCase(), pageable);
    }
}

