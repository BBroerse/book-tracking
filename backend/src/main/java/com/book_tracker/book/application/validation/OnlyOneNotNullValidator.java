package com.book_tracker.book.application.validation;

import com.book_tracker.book.application.dto.BookRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OnlyOneNotNullValidator implements ConstraintValidator<OnlyOneNotNull, BookRequestDto> {

    @Override
    public boolean isValid(BookRequestDto bookRequest, ConstraintValidatorContext context) {
        if (bookRequest.isbn() != null && bookRequest.title() != null) {
            return false;
        }

        return bookRequest.isbn() != null || bookRequest.title() != null;
    }
}
