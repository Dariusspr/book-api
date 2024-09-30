package com.app.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findResponseByISBN_validISBN() {
        final String isbn = "9783161484100";
        final String title = "The Great Adventure";
        final Long categoryId = 1L;
        final String categoryTitle = "Fiction";
        final int year = 2021;
        final int pages = 350;
        final double rating = 5.0d;
        final Long reviewCount = 4L;
        final String authorsRaw = "1,John,Doe;3,Emily,Johnson";

        Object[] row = bookRepository.findResponseByISBN(isbn);
        assertNotEquals(0, row.length);

        Object[] bookDetails = (Object[]) row[0];
        assertEquals(9, bookDetails.length);
        assertEquals(isbn, bookDetails[0]);
        assertEquals(categoryTitle, bookDetails[1]);
        assertEquals(categoryId, bookDetails[2]);
        assertEquals(title, bookDetails[3]);
        assertEquals(authorsRaw, bookDetails[4]);
        assertEquals(year, bookDetails[5]);
        assertEquals(pages, bookDetails[6]);
        assertEquals(rating, bookDetails[7]);
        assertEquals(reviewCount, bookDetails[8]);
    }

    @Test
    void findResponseByISBN_invalidISBN() {
        String invalidIsbn = "0000000000000";

        Object[] result = bookRepository.findResponseByISBN(invalidIsbn);

        assertEquals(0, result.length);
    }
}
