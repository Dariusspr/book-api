package com.app.mappers;

import com.app.dtos.responses.AuthorResponse;
import com.app.dtos.responses.BookResponse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class BookMapper {
    private BookMapper() {}

    public static BookResponse toResponse(Object[] row) {
        String isbn = (String) row[0];                              // ISBN
        String categoryTitle = (String) row[1];                     // Category title
        Long categoryId = (Long) row[2];                            // Category id
        String title = (String) row[3];                             // Book title
        String authorsRaw = (String) row[4];                        // Authors (concatenated)
        Integer year = (Integer) row[5];                            // Year
        Integer pages = (Integer) row[6];                           // Pages
        double averageRating = ((BigDecimal)row[7]).doubleValue();  // Average rating
        long reviewCount = (Long) row[8];                           // Review count

        List<AuthorResponse> authors;
        if (authorsRaw != null) {
            authors = Arrays.stream(authorsRaw.split("#"))
                    .map(AuthorMapper::toResponse)
                    .toList();
        } else {
            authors = null;
        }
        return new BookResponse(
                isbn, CategoryMapper.toResponse(categoryId, categoryTitle), title, authors, year,
                pages, averageRating, reviewCount);
    }
}

