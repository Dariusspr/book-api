package com.app.controllers.publ;

import com.app.constants.EndpointConstants;
import com.app.dtos.requests.ReviewRequest;
import com.app.dtos.responses.ReviewResponse;
import com.app.services.ReviewService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("publReviewController")
@RequestMapping(ReviewController.BASE_PATH)
public class ReviewController {
    public static final String BASE_PATH = EndpointConstants.PUBLIC_API + "/reviews";

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{isbn}/book")
    public ResponseEntity<Page<ReviewResponse>> getAllByBooks(@PathVariable String isbn, Pageable pageable) {
        var review = reviewService.getResponsesByBook(isbn, pageable);
        return ResponseEntity.ok(review);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable Long id) {
        var review = reviewService.getResponseById(id);
        return ResponseEntity.ok(review);
    }

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@RequestBody @Valid ReviewRequest request) {
        var review = reviewService.create(request);
        return ResponseEntity.ok(review);
    }
}