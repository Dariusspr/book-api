package com.app.controllers;

import com.app.controllers.publ.CategoryController;
import com.app.dtos.responses.CategoryResponse;
import com.app.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private final CategoryResponse mockCategoryResponse = new CategoryResponse(1L, "Test");

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Test
    void getAllCategories() throws Exception {
        Page<CategoryResponse> mockPage = new PageImpl<>(List.of(mockCategoryResponse));

        when(categoryService.getResponses(Pageable.ofSize(DEFAULT_PAGE_SIZE))).thenReturn(mockPage);

        mockMvc.perform(get(CategoryController.BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].title").value(mockCategoryResponse.title()));

        verify(categoryService).getResponses(Pageable.ofSize(DEFAULT_PAGE_SIZE));
    }

    @Test
    void getCategoryByTitle() throws Exception {
        Page<CategoryResponse> mockPage = new PageImpl<>(List.of(mockCategoryResponse));

        when(categoryService.getResponsesByTitle(mockCategoryResponse.title(), Pageable.ofSize(DEFAULT_PAGE_SIZE))).thenReturn(mockPage);

        mockMvc.perform(get(CategoryController.BASE_PATH + "/" + mockCategoryResponse.title() + "/title")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].title").value(mockCategoryResponse.title()));

        verify(categoryService).getResponsesByTitle(mockCategoryResponse.title(), Pageable.ofSize(DEFAULT_PAGE_SIZE));
    }

    @Test
    void getCategoryById() throws Exception {
        when(categoryService.getResponseById(mockCategoryResponse.id())).thenReturn(mockCategoryResponse);

        mockMvc.perform(get(CategoryController.BASE_PATH + "/" + mockCategoryResponse.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockCategoryResponse.id()))
                .andExpect(jsonPath("$.title").value(mockCategoryResponse.title()));

        verify(categoryService).getResponseById(mockCategoryResponse.id());
    }
}
