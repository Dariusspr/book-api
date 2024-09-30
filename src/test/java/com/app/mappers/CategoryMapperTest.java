package com.app.mappers;

import com.app.dtos.responses.CategoryResponse;
import com.app.entities.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryMapperTest {
    private final String title = "category";
    private final Long id = 1L;
    private final CategoryResponse expected = new CategoryResponse(id, title);

    @Test
    void toResponse_Category() {
        Category category = new Category(title);
        category.setId(id);

        CategoryResponse actual = CategoryMapper.toResponse(category);

        assertEquals(expected ,actual);
    }

    @Test
    void toResponse_fields() {
        CategoryResponse actual = CategoryMapper.toResponse(id, title);

        assertEquals(expected ,actual);
    }
}
