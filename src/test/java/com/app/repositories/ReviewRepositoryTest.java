package com.app.repositories;

import com.app.dtos.responses.ReviewResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    private final Pageable pageable = PageRequest.of(0, 1);

    @Test
    void findResponsesByBook_validIsbn() {
        final String isbnToSearch = "9783161484100";

        List<ReviewResponse> list = reviewRepository.findResponsesByBook(isbnToSearch, pageable)
                .getContent();

        assertFalse(list.isEmpty());
        ReviewResponse actual = list.get(0);
        assertNotNull(actual.comment());
        assertNotNull(actual.rating());
        assertNotNull(actual.id());
    }

    @Test
    void findResponsesByBook_noReviews() {
        String isbnToSearch = "0000000000000";

        List<ReviewResponse> list = reviewRepository.findResponsesByBook(isbnToSearch, pageable)
                .getContent();

        assertTrue(list.isEmpty());
    }
}
