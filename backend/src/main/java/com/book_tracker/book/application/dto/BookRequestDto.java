package com.book_tracker.book.application.dto;

import com.book_tracker.book.application.validation.OnlyOneNotNull;
import jakarta.validation.constraints.Pattern;

@OnlyOneNotNull
public record BookRequestDto(
        String title,

        @Pattern(regexp = "^[0-9]*$", message = "ISBN must be a number")
        String isbn
) { }

