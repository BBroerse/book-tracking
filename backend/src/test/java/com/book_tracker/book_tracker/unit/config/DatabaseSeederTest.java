package com.book_tracker.book_tracker.unit.config;

import com.book_tracker.book.domain.Book;
import com.book_tracker.book.domain.BookRepository;
import com.book_tracker.config.DatabaseSeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Profile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Profile("dev")
public class DatabaseSeederTest {

    private BookRepository bookRepository;
    private DatabaseSeeder databaseSeeder;

    @BeforeEach
    void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        databaseSeeder = new DatabaseSeeder(bookRepository, 100);
    }

    @Test
    void run_shouldGenerateBooks_whenRepositoryIsEmpty() {
        when(bookRepository.count()).thenReturn(0L);

        databaseSeeder.run();

        verify(bookRepository, times(100)).save(any(Book.class));
    }

    @Test
    void run_shouldNotGenerateBooks_whenRepositoryIsNotEmpty() {
        when(bookRepository.count()).thenReturn(1L);

        databaseSeeder.run();

        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    void generateBooks_shouldGenerateSpecifiedNumberOfBooks() {
        int count = 50;

        databaseSeeder.generateBooks(count);

        verify(bookRepository, times(count)).save(any(Book.class));
    }
}