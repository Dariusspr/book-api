package com.app.services;


import com.app.dtos.requests.BookFilterRequest;
import com.app.dtos.responses.BookResponse;
import com.app.entities.Book;
import com.app.exceptions.BookNotFoundException;
import com.app.mappers.BookMapper;
import com.app.repositories.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookResponse> getResponses(BookFilterRequest filters, Pageable pageable) {
        Page<Object[]> rows = bookRepository.findResponses(
                filters.title(),
                filters.minRating(),
                filters.maxRating(),
                filters.minReviewCount(),
                filters.maxReviewCount(),
                filters.categoryId(),
                filters.authorId(),
                filters.minYear(),
                filters.maxYear(),
                filters.minPages(),
                filters.maxPages(),
                pageable
        );
        return rows.map(BookMapper::toResponse);
    }



    public BookResponse getResponseByISBN(String isbn) {
        Object[] result = bookRepository.findResponseByISBN(isbn);
        if (result.length == 0) {
            throw new BookNotFoundException();
        }

        // Returns Object[][] - multiple rows, get first row
        Object[] row = (Object[]) result[0];
        if (row.length != 9) {
            throw new IllegalStateException();
        }
        return BookMapper.toResponse(row);
    }


    public Book getByISBN(String isbn) {
        return bookRepository.findById(isbn)
                .orElseThrow(BookNotFoundException::new);
    }
}
