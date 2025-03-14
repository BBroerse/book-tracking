package com.book_tracker.config;

import com.book_tracker.book.domain.Book;
import com.book_tracker.book.domain.BookRepository;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    private static final int DEFAULT_AMOUNT_OF_BOOKS = 100;
    private final int AMOUNT_OF_BOOKS;
    
    private final BookRepository bookRepository;
    private final Faker faker = new Faker(new Random());

    public DatabaseSeeder(BookRepository bookRepository) {
        this(bookRepository, DEFAULT_AMOUNT_OF_BOOKS);
    }

    public DatabaseSeeder(BookRepository bookRepository, int AMOUNT_OF_BOOKS) {
        this.bookRepository = bookRepository;
        this.AMOUNT_OF_BOOKS = AMOUNT_OF_BOOKS;
    }

    @Override
    @Transactional
    public void run(String... args) {
        if (bookRepository.count() == 0) {
            generateBooks(AMOUNT_OF_BOOKS);
        }
    }

    public void generateBooks(int count) {
        IntStream.range(0, count).forEach(i -> {
            Book book = new Book();

            book.setTitle(faker.book().title());
            book.setAuthor(faker.book().author());
            book.setIsbn(i);

            bookRepository.save(book);
        });
    }
}
