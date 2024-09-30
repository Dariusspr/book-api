package com.app.repositories;

import com.app.dtos.responses.ReviewResponse;
import com.app.entities.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT new com.app.dtos.responses.ReviewResponse(r.id, r.rating, r.comment)\s
        FROM Review  r\s
        WHERE r.book.isbn = :isbn
        """)
    Page<ReviewResponse> findResponsesByBook(@Param("isbn") String isbn, Pageable pageable);
}
