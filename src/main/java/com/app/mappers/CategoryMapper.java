package com.app.mappers;

import com.app.dtos.responses.CategoryResponse;
import com.app.entities.Category;

public class CategoryMapper {
    private CategoryMapper() {}


    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getTitle());
    }

    public static CategoryResponse toResponse(Long categoryId, String categoryTitle) {
        return new CategoryResponse(categoryId, categoryTitle);
    }
}
