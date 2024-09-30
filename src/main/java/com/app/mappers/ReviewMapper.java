package com.app.mappers;

import com.app.dtos.responses.ReviewResponse;
import com.app.entities.Review;

public class ReviewMapper {
    private ReviewMapper() {}

    public static ReviewResponse toResponse(Review review) {
        return new ReviewResponse(review.getId(), review.getRating(),
                review.getComment());
    }
}


