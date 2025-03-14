package com.book_tracker.book_tracker.unit.book.application.validation;

import com.book_tracker.book.application.dto.BookRequestDto;
import com.book_tracker.book.application.validation.OnlyOneNotNullValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OnlyOneNotNullValidatorTest {

    private OnlyOneNotNullValidator validator;

    @BeforeEach
    void setUp() {
        validator = new OnlyOneNotNullValidator();
    }

    @Test
    void isValid_shouldReturnTrue_whenIsbnIsNotNull() {
        BookRequestDto bookRequest = new BookRequestDto("123456", null);
        assertTrue(validator.isValid(bookRequest, null));
    }

    @Test
    void isValid_shouldReturnTrue_whenTitleIsNotNull() {
        BookRequestDto bookRequest = new BookRequestDto(null, "Test Title");
        assertTrue(validator.isValid(bookRequest, null));
    }

    @Test
    void isValid_shouldReturnFalse_whenBothIsbnAndTitleAreNotNull() {
        BookRequestDto bookRequest = new BookRequestDto("123456", "Test Title");
        assertFalse(validator.isValid(bookRequest, null));
    }

    @Test
    void isValid_shouldReturnFalse_whenBothIsbnAndTitleAreNull() {
        BookRequestDto bookRequest = new BookRequestDto(null, null);
        assertFalse(validator.isValid(bookRequest, null));
    }
}