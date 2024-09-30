package com.app.controllers;

import com.app.controllers.publ.ReviewController;
import com.app.dtos.requests.ReviewRequest;
import com.app.dtos.responses.ReviewResponse;
import com.app.services.ReviewService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    private final ReviewResponse mockReviewResponse = new ReviewResponse(1L, 3, "Comment");
    private final ReviewRequest mockReviewRequest = new ReviewRequest("1234567890123", 3, "Comment");

    private static final int DEFAULT_PAGE_SIZE = 20;

    @Test
    void getAllReviewsByBookIsbn() throws Exception {
        Page<ReviewResponse> mockPage = new PageImpl<>(List.of(mockReviewResponse));

        when(reviewService.getResponsesByBook(mockReviewRequest.bookIsbn(), Pageable.ofSize(DEFAULT_PAGE_SIZE))).thenReturn(mockPage);

        mockMvc.perform(get(ReviewController.BASE_PATH + "/"+ mockReviewRequest.bookIsbn() +"/book")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].comment").value(mockReviewRequest.comment()));

        verify(reviewService).getResponsesByBook(mockReviewRequest.bookIsbn(), Pageable.ofSize(DEFAULT_PAGE_SIZE));
    }

    @Test
    void getReviewById() throws Exception {
        when(reviewService.getResponseById(1L)).thenReturn(mockReviewResponse);

        mockMvc.perform(get(ReviewController.BASE_PATH + "/" + mockReviewResponse.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockReviewResponse.id()))
                .andExpect(jsonPath("$.comment").value(mockReviewResponse.comment()));

        verify(reviewService).getResponseById(mockReviewResponse.id());
    }

    @Test
    void createReview() throws Exception {
        when(reviewService.create(mockReviewRequest)).thenReturn(mockReviewResponse);

        String jsonRequest = String.format("""
                                {
                                    "bookIsbn": "%s",
                                    "rating": %d,
                                    "comment": "%s"
                                }
                                """, mockReviewRequest.bookIsbn(), mockReviewRequest.rating(), mockReviewRequest.comment());

        mockMvc.perform(post(ReviewController.BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(mockReviewResponse.id()))
                .andExpect(jsonPath("$.comment").value(mockReviewResponse.comment()));

        verify(reviewService).create(mockReviewRequest);
    }
}

