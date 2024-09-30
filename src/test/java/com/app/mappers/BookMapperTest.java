package com.app.mappers;


import com.app.dtos.responses.AuthorResponse;
import com.app.dtos.responses.BookResponse;
import com.app.dtos.responses.CategoryResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookMapperTest {

    private final String isbn = "9780123456789";
    private final Long categoryId = 1L;
    private final String categoryTitle = "Science Fiction";
    private final String title = "Journey";
    private final Long author1Id = 10L;
    private final String author1FirstName = "Emily";
    private final String author1LastName = "Johnson";
    private final Long author2Id = 14L;
    private final String author2FirstName = "Emma";
    private final String author2LastName = "Waa";
    private final Integer year = 2000;
    private final Integer pages = 150;
    private final BigDecimal averageRating = BigDecimal.valueOf(4.31);
    private final long reviewCount = 213;


    private final BookResponse expectedWithSingleAuthor = new BookResponse(isbn,
            title,
            new CategoryResponse(categoryId, categoryTitle),
            List.of(new AuthorResponse(author1Id, author1FirstName, author1LastName)),
            year,
            pages,
            averageRating.doubleValue(),
            reviewCount);
    private final BookResponse ExpectedWithMultipleAuthors = new BookResponse(isbn,
            title,
            new CategoryResponse(categoryId, categoryTitle),
            List.of(new AuthorResponse(author1Id, author1FirstName, author1LastName),
                    new AuthorResponse(author2Id, author2FirstName, author2LastName),
                    new AuthorResponse(author1Id, author1FirstName, author1LastName)),
            year,
            pages,
            averageRating.doubleValue(),
            reviewCount);
    private final BookResponse expectedWithNoAuthors = new BookResponse(isbn,
            title,
            new CategoryResponse(categoryId, categoryTitle),
            null,
            year,
            pages,
            averageRating.doubleValue(),
            reviewCount);

    private final String author1 = String.join(",", author1Id.toString(), author1FirstName, author1LastName);
    private final String author2 = String.join(",", author2Id.toString(), author2FirstName, author2LastName);

    @Test
    void toResponse_Object_noAuthors() {
        Object[] bookResponseRow = new Object[] {
                isbn,
                categoryTitle,
                categoryId,
                title,
                null,
                year,
                pages,
                averageRating,
                reviewCount
        };

        BookResponse actual = BookMapper.toResponse(bookResponseRow);

        assertEquals(expectedWithNoAuthors, actual);
    }


    @Test
    void toResponse_Object_singleAuthor() {
        Object[] bookResponseRow = new Object[] {
                isbn,
                categoryTitle,
                categoryId,
                title,
                author1,
                year,
                pages,
                averageRating,
                reviewCount
        };

        BookResponse actual = BookMapper.toResponse(bookResponseRow);

        assertEquals(expectedWithSingleAuthor, actual);
    }

    @Test
    void toResponse_Object_multipleAuthors() {
        Object[] bookResponseRow = new Object[] {
                isbn,
                categoryTitle,
                categoryId,
                title,
                String.join("#", author1, author2, author1),
                year,
                pages,
                averageRating,
                reviewCount
        };

        BookResponse actual = BookMapper.toResponse(bookResponseRow);

        assertEquals(ExpectedWithMultipleAuthors, actual);
    }
}
