package com.book_tracker.book.application;

import com.book_tracker.book.application.dto.BookResponseDto;
import com.book_tracker.book.application.exception.BookNotFoundException;
import com.book_tracker.book.domain.Book;
import com.book_tracker.book.domain.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookResponseDto getBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbn(Integer.parseInt(isbn)).orElseThrow(BookNotFoundException::new);
        return mapToResponseDto(book);
    }

    public BookResponseDto getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title).orElseThrow(BookNotFoundException::new);
        return mapToResponseDto(book);
    }

    private BookResponseDto mapToResponseDto(Book book) {
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn());
    }
}
