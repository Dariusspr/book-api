package com.app.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

public record BookFilterRequest(
        @Length(max = 255) String title,
        @Min(1) @Max(5) Double minRating,
        @Min(1) @Max(5) Double maxRating,
        @PositiveOrZero Integer minReviewCount,
        @PositiveOrZero Integer maxReviewCount,
        @PositiveOrZero Long categoryId,
        @PositiveOrZero Long authorId,
        @Positive Integer minYear,
        @Positive Integer maxYear,
        @Positive Integer minPages,
        @Positive Integer maxPages) {
}