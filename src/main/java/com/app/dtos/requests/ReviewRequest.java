package com.app.dtos.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;

public record ReviewRequest(
        String bookIsbn,
        @Min(1) @Max(5) int rating,
        @Length(max = 255) String comment) {
}
