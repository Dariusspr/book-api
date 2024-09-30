package com.app.services;

import com.app.dtos.requests.ReviewRequest;
import com.app.dtos.responses.ReviewResponse;
import com.app.entities.Book;
import com.app.entities.Review;
import com.app.exceptions.ReviewNotFoundException;
import com.app.mappers.ReviewMapper;
import com.app.repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookService bookService;

    public ReviewService(ReviewRepository reviewRepository, BookService bookService) {
        this.reviewRepository = reviewRepository;
        this.bookService = bookService;
    }

    public Review getById(Long id) {
         return  reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);
    }

    public ReviewResponse getResponseById(Long id) {
        return ReviewMapper.toResponse(getById(id));

    }

    public Page<ReviewResponse> getResponsesByBook(String isbn, Pageable pageable) {
        return reviewRepository.findResponsesByBook(isbn, pageable);
    }

    @Transactional
    public ReviewResponse create(ReviewRequest request) {
        Book book = bookService.getByISBN(request.bookIsbn());
        Review review = new Review(book, request.rating(), request.comment());
        reviewRepository.save(review);
        return ReviewMapper.toResponse(review);
    }
}