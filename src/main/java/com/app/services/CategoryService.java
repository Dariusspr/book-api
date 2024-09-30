package com.app.services;

import com.app.dtos.responses.CategoryResponse;
import com.app.entities.Category;
import com.app.exceptions.CategoryNotFoundException;
import com.app.mappers.CategoryMapper;
import com.app.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<CategoryResponse> getResponses(Pageable pageable) {
        return categoryRepository.findResponses(pageable);
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
    }

    public CategoryResponse getResponseById(Long id) {
        return CategoryMapper.toResponse(getById(id));
    }

    public Page<CategoryResponse> getResponsesByTitle(String title, Pageable pageable) {
        return categoryRepository.findByTitle(title.toLowerCase(), pageable);
    }
}