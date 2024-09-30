package com.app.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.*;

import com.app.controllers.publ.AuthorController;
import com.app.dtos.responses.AuthorResponse;
import com.app.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @InjectMocks
    private AuthorController authorController;

    private final AuthorResponse mockResponse = new AuthorResponse(1L, "mock1", "mock2");

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Test
    void getAllAuthors() throws Exception {
        final Page<AuthorResponse> mockPage = new PageImpl<>(List.of(mockResponse));

        when(authorService.getResponses(any(Pageable.class))).thenReturn(mockPage);

        mockMvc.perform(get(AuthorController.BASE_PATH)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));

        verify(authorService).getResponses(any(Pageable.class));
    }

    @Test
    void getAuthorByName() throws Exception {
        Page<AuthorResponse> mockPage = new PageImpl<>(List.of(mockResponse));
        when(authorService.getResponseByName(mockResponse.firstName(), Pageable.ofSize(DEFAULT_PAGE_SIZE))).thenReturn(mockPage);

        mockMvc.perform(get(AuthorController.BASE_PATH + "/" + mockResponse.firstName() + "/name")
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));

        verify(authorService).getResponseByName(mockResponse.firstName(), Pageable.ofSize(DEFAULT_PAGE_SIZE));
    }

    @Test
    void getAuthorById() throws Exception {
        when(authorService.getResponseById(mockResponse.id())).thenReturn(mockResponse);

        mockMvc.perform(get(AuthorController.BASE_PATH + "/" + mockResponse.id())
                        .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));

        verify(authorService).getResponseById(mockResponse.id());
    }
}
