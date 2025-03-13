package com.book_tracker.book.application;

import com.book_tracker.book.application.dto.BookResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<BookResponseDto> getBook(
            @RequestParam(required = false) Integer isbn,
            @RequestParam(required = false) String title
    ) {
        if (isbn != null) {
            return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
        } else if (title != null) {
            return ResponseEntity.ok(bookService.getBookByTitle(title));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
