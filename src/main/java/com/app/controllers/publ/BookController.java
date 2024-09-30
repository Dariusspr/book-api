package com.app.controllers.publ;

import com.app.constants.EndpointConstants;
import com.app.dtos.requests.BookFilterRequest;
import com.app.dtos.responses.BookResponse;
import com.app.services.BookService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("publBookController")
@RequestMapping(BookController.BASE_PATH)
public class BookController {
    public static final String BASE_PATH = EndpointConstants.PUBLIC_API + "/books";

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<BookResponse>> getAll(@Valid BookFilterRequest filters, Pageable pageable) {
        return ResponseEntity.ok(bookService.getResponses(filters, pageable));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponse> getByISBN(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.getResponseByISBN(isbn));
    }
}
