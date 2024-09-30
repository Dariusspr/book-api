package com.app.services;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.app.dtos.requests.BookFilterRequest;
import com.app.dtos.responses.BookResponse;
import com.app.entities.Book;
import com.app.exceptions.BookNotFoundException;
import com.app.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private final String isbn = "1234567890123";

    @Test
    void getResponses() {
        BookFilterRequest filters = new BookFilterRequest("title", 1.0, 5.0,
                10, 50, 1L,
                1L,2000, 2024,
                100, 500);
        Pageable pageable = Pageable.ofSize(10);
        Page<Object[]> mockPage = mock(Page.class);
        when(bookRepository.findResponses(anyString(), anyDouble(), anyDouble(), anyInt(), anyInt(),
                anyLong(), anyLong(), anyInt(), anyInt(), anyInt(), anyInt(), any()))
                .thenReturn(mockPage);

        assertDoesNotThrow(() -> bookService.getResponses(filters, pageable));

        verify(bookRepository).findResponses(anyString(), anyDouble(), anyDouble(), anyInt(), anyInt(),
                anyLong(), anyLong(), anyInt(), anyInt(), anyInt(), anyInt(), any());
        verify(mockPage).map(any());
    }

    @Test
    void getResponseByISBN_ValidISBN() {
        String isbn = "1234567890123";
        String categoryTitle = "Fiction";
        long categoryId = 1L;
        String title = "Mock Book Title";
        int year = 2022;
        int pages = 300;
        BigDecimal averageRating = BigDecimal.valueOf(4.5);
        long reviewCount = 150L;

        Object[] mockRow = new Object[] {
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

        when(bookRepository.findResponseByISBN(isbn)).thenReturn(new Object[][]{mockRow});

        assertDoesNotThrow(() -> bookService.getResponseByISBN(isbn));

        verify(bookRepository).findResponseByISBN(isbn);
    }

    @Test
    void getResponseByISBN_invalidISBN() {
        when(bookRepository.findResponseByISBN(isbn)).thenReturn(new Object[0][]);

        assertThrows(BookNotFoundException.class, () -> bookService.getResponseByISBN(isbn));
        verify(bookRepository).findResponseByISBN(isbn);
    }

    @Test
    void getResponseByISBN_InvalidRowLength() {
        Object[] mockRow = new Object[8];
        when(bookRepository.findResponseByISBN(isbn)).thenReturn(new Object[][]{mockRow});

        assertThrows(IllegalStateException.class, () -> bookService.getResponseByISBN(isbn));
        verify(bookRepository).findResponseByISBN(isbn);
    }

    @Test
    void getByISBN_validISBN() {
        Book mockBook = new Book();
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(mockBook));

        Book book = bookService.getByISBN(isbn);

        assertNotNull(book);
        verify(bookRepository).findById(isbn);
    }

    @Test
    void getByISBN_invalidISBN() {
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getByISBN(isbn));
        verify(bookRepository).findById(isbn);
    }
}

