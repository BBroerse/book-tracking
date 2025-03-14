package com.book_tracker.book.application;

import com.book_tracker.book.application.dto.BookRequestDto;
import com.book_tracker.book.application.dto.BookResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getBook(@Valid BookRequestDto bookRequest) {
        if (bookRequest.isbn() != null) {
            return ResponseEntity.ok(bookService.getBookByIsbn(bookRequest.isbn()));
        } else {
            return ResponseEntity.ok(bookService.getBookByTitle(bookRequest.title()));
        }
    }
}
