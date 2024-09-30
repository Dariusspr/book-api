package com.app.controllers;

import com.app.controllers.publ.BookController;
import com.app.dtos.requests.BookFilterRequest;
import com.app.dtos.responses.BookResponse;
import com.app.services.BookService;
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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    private final BookResponse mockBookResponse = new BookResponse("1234567890123", "Title",null,
            null, 2021, 500,
            4.5, 100);

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Test
    void getAllBooks() throws Exception {
        BookFilterRequest filters = new BookFilterRequest(null, null, null,
                null, null,
                null, null, null,
                null, null, null);
        Page<BookResponse> mockPage = new PageImpl<>(List.of(mockBookResponse));

        when(bookService.getResponses(filters, Pageable.ofSize(DEFAULT_PAGE_SIZE))).thenReturn(mockPage);

        mockMvc.perform(get(BookController.BASE_PATH)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1));

        verify(bookService).getResponses(filters, Pageable.ofSize(DEFAULT_PAGE_SIZE));
    }

    @Test
    void getBookByISBN() throws Exception {
        when(bookService.getResponseByISBN(mockBookResponse.isbn())).thenReturn(mockBookResponse);

        mockMvc.perform(get(BookController.BASE_PATH + "/" + mockBookResponse.isbn())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.isbn").value(mockBookResponse.isbn()))
                .andExpect(jsonPath("$.title").value(mockBookResponse.title()));

        verify(bookService).getResponseByISBN(mockBookResponse.isbn());
    }
}
