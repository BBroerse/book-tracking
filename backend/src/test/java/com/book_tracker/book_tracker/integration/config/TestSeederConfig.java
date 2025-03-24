package com.book_tracker.book_tracker.integration.config;

import com.book_tracker.book.domain.BookRepository;
import com.book_tracker.config.DatabaseSeeder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestSeederConfig {

    @Bean
    public DatabaseSeeder databaseSeeder(BookRepository bookRepository) {
        return new DatabaseSeeder(bookRepository);
    }
}
