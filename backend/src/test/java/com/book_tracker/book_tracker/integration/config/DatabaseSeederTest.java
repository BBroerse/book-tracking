package com.book_tracker.book_tracker.integration.config;

import com.book_tracker.config.DatabaseSeeder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(TestSeederConfig.class)
@Testcontainers
@ActiveProfiles("test")
class DatabaseSeederTest {

    @Container
    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private DatabaseSeeder seeder;

    @Autowired
    private JdbcTemplate jdbcTemplate; // Directly querying the database

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        // Ideally we use @Transactional, but it doesn't work well with DatabaseSeeder
        jdbcTemplate.execute("TRUNCATE books");
    }

    @Test
    void run_shouldSeedDatabaseWithDefaultAmountOfBooks_whenDatabaseIsEmpty() {
        seeder.run();

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books", Integer.class);
        assertEquals(100, count);
    }

    @Test
    void run_shouldNotSeedDatabase_whenDatabaseIsNotEmpty() {
        jdbcTemplate.update("INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)", "Existing Book", "Existing Author", 123456);

        seeder.run();

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books", Integer.class);
        assertEquals(1, count);
    }

    @Test
    void generateBooks_shouldSeedDatabaseWithSpecifiedAmountOfBooks() {
        seeder.generateBooks(50);

        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM books", Integer.class);
        assertEquals(50, count);
    }
}
