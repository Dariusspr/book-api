package com.app.dtos.responses;

import java.util.List;

public record BookResponse(String isbn, CategoryResponse category, String title,
                           List<AuthorResponse> authors, int year, int pages,
                           double rating, long reviewCount) {
}
