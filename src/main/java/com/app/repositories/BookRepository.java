package com.app.repositories;


import com.app.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, String> {
    @Query(value = """
            SELECT b.isbn,
                   c.title,
                   c.id,
                   b.title,
                   GROUP_CONCAT(DISTINCT CONCAT(a.id, ',', a.first_name, ',', a.last_name) SEPARATOR ': ') AS authors,
                   b.published_year,
                   b.pages,
                   AVG(r.rating) AS average_rating,
                   COUNT(r.id) AS review_count
            FROM book b
            LEFT JOIN category c ON b.category_id = c.id
            LEFT JOIN book_author ba ON b.isbn = ba.book_isbn
            LEFT JOIN author a ON ba.author_id = a.id
            LEFT JOIN review r ON b.isbn = r.book_isbn
            WHERE (:minYear IS NULL OR b.published_year >= :minYear)
            AND (:maxYear IS NULL OR b.published_year <= :maxYear)
            AND (:minPages IS NULL OR b.pages >= :minPages)
            AND (:maxPages IS NULL OR b.pages <= :maxPages)
            AND (:categoryId IS NULL OR c.id = :categoryId)
            AND (:authorId IS NULL OR a.id = :authorId)
            AND (:title IS NULL OR b.title LIKE %:title%)
            GROUP BY b.isbn, c.id, c.title, b.title, b.published_year, b.pages
            HAVING (:minRating IS NULL OR average_rating >= :minRating)
            AND (:maxRating IS NULL OR average_rating <= :maxRating)
            AND (:minReviewCount IS NULL OR review_count >= :minReviewCount)
            AND (:maxReviewCount IS NULL OR review_count <= :maxReviewCount)
            """,
            countQuery = "SELECT COUNT(*) FROM book",
            nativeQuery = true)
    Page<Object[]> findResponses(
            @Param("title") String title,
            @Param("minRating") Double minRating,
            @Param("maxRating") Double maxRating,
            @Param("minReviewCount") Integer minReviewCount,
            @Param("maxReviewCount") Integer maxReviewCount,
            @Param("categoryId") Long categoryId,
            @Param("authorId") Long authorId,
            @Param("minYear") Integer minYear,
            @Param("maxYear") Integer maxYear,
            @Param("minPages") Integer minPages,
            @Param("maxPages") Integer maxPages,
            Pageable pageable
    );

    @Query(value = """
            SELECT b.isbn,
                   c.title,
                   c.id,
                   b.title,
                   GROUP_CONCAT(DISTINCT CONCAT(a.id, ',', a.first_name, ',', a.last_name) SEPARATOR ': ') AS authors,
                   b.published_year,
                   b.pages,
                   AVG(r.rating) AS average_rating,
                   COUNT(r.id) AS review_count
            FROM book b
            LEFT JOIN category c
                ON b.category_id = c.id
            LEFT JOIN book_author ba
                ON b.isbn = ba.book_isbn
            LEFT JOIN author a
                ON ba.author_id = a.id
            LEFT JOIN review r
                ON b.isbn = r.book_isbn
            WHERE b.isbn = :isbn
            GROUP BY b.isbn, c.id, c.title, b.title, b.published_year, b.pages
            """, nativeQuery = true)
    Optional<Object[]> findResponseByISBN(@Param("isbn") String isbn);
}
