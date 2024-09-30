package com.app.services;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.app.dtos.requests.ReviewRequest;
import com.app.dtos.responses.ReviewResponse;
import com.app.entities.Book;
import com.app.entities.Review;
import com.app.exceptions.ReviewNotFoundException;
import com.app.repositories.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReviewService reviewService;

    private final long reviewId = 1L;
    private int reviewRating;
    private final String bookIsbn = "123456789012";
    private final String reviewComment = "Great Book!";

    @Test
    void getById_validId() {
        Review review = new Review();
        review.setId(reviewId);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        Review actualReview = reviewService.getById(reviewId);

        assertEquals(reviewId, actualReview.getId());
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void getById_invalidId() {
        Long reviewId = 1000L;
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        assertThrows(ReviewNotFoundException.class, () -> reviewService.getById(reviewId));
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void getResponseById() {
        Review review = new Review();
        review.setId(reviewId);
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewResponse actualResponse = reviewService.getResponseById(reviewId);

        assertEquals(reviewId, actualResponse.id());
        verify(reviewRepository, times(1)).findById(reviewId);
    }

    @Test
    void getResponsesByBook() {
        Pageable pageable = Pageable.ofSize(1);
        reviewRating = 5;
        ReviewResponse reviewResponse = new ReviewResponse(reviewId, reviewRating, reviewComment);
        Page<ReviewResponse> expectedPage = new PageImpl<>(List.of(reviewResponse));

        when(reviewRepository.findResponsesByBook(bookIsbn, pageable)).thenReturn(expectedPage);

        Page<ReviewResponse> actualPage = reviewService.getResponsesByBook(bookIsbn, pageable);

        assertEquals(expectedPage, actualPage);
        verify(reviewRepository, times(1)).findResponsesByBook(bookIsbn, pageable);
    }

    @Test
    void create() {
        ReviewRequest request = new ReviewRequest(bookIsbn, reviewRating, reviewComment);
        Book book = new Book();
        book.setIsbn(bookIsbn);

        when(bookService.getByISBN(request.bookIsbn())).thenReturn(book);

        Review review = new Review(book, request.rating(), request.comment());
        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        ReviewResponse actualResponse = reviewService.create(request);

        assertEquals(reviewRating, actualResponse.rating());
        assertEquals(reviewComment, actualResponse.comment());
        verify(bookService, times(1)).getByISBN(request.bookIsbn());
        verify(reviewRepository, times(1)).save(any(Review.class));
    }
}
