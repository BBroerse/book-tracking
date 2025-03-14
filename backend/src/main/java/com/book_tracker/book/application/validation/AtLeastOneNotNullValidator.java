package com.book_tracker.book.application.validation;

import com.book_tracker.book.application.dto.BookRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, BookRequestDto> {

    @Override
    public void initialize(AtLeastOneNotNull constraintAnnotation) {}

    @Override
    public boolean isValid(BookRequestDto bookRequest, ConstraintValidatorContext context) {
        return bookRequest.isbn() != null || bookRequest.title() != null;
    }
}
