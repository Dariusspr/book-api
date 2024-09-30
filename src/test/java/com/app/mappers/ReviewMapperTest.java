package com.app.mappers;

import com.app.dtos.responses.ReviewResponse;
import com.app.entities.Book;
import com.app.entities.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewMapperTest {

    private final Long id = 1L;
    private final Book book = new Book();
    private final int rating = 2;
    private final String comment = "Comment...";
    private final ReviewResponse expected = new ReviewResponse(id, rating, comment);

    @Test
    void toResponse_Review() {
        Review review = new Review(book, rating, comment);
        review.setId(id);

        ReviewResponse actual = ReviewMapper.toResponse(review);

        assertEquals(expected, actual);
    }
}
