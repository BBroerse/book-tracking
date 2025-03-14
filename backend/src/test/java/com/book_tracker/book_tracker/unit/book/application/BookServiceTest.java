package com.book_tracker.book_tracker.unit.book.application;

import com.book_tracker.book.application.BookService;
import com.book_tracker.book.application.dto.BookResponseDto;
import com.book_tracker.book.application.exception.BookNotFoundException;
import com.book_tracker.book.domain.Book;
import com.book_tracker.book.domain.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookRepository bookRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void getBookByIsbn_shouldReturnBook_whenBookExists() {
        Book book = new Book(1L, "Test Title", "Test Author", 123456);
        when(bookRepository.findByIsbn(123456)).thenReturn(Optional.of(book));

        BookResponseDto response = bookService.getBookByIsbn("123456");

        assertNotNull(response);
        assertEquals("Test Title", response.title());
        assertEquals("Test Author", response.author());
        assertEquals(123456, response.isbn());
    }

    @Test
    void getBookByIsbn_shouldThrowException_whenBookDoesNotExist() {
        when(bookRepository.findByIsbn(123456)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByIsbn("123456"));
    }

    @Test
    void getBookByTitle_shouldReturnBook_whenBookExists() {
        Book book = new Book(1L, "Test Title", "Test Author", 123456);
        when(bookRepository.findByTitle("Test Title")).thenReturn(Optional.of(book));

        BookResponseDto response = bookService.getBookByTitle("Test Title");

        assertNotNull(response);
        assertEquals("Test Title", response.title());
        assertEquals("Test Author", response.author());
        assertEquals(123456, response.isbn());
    }

    @Test
    void getBookByTitle_shouldThrowException_whenBookDoesNotExist() {
        when(bookRepository.findByTitle("Test Title")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookByTitle("Test Title"));
    }
}
