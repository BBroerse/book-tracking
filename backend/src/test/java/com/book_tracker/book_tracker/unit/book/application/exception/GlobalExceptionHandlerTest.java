package com.book_tracker.book_tracker.unit.book.application.exception;

import com.book_tracker.book.application.dto.ErrorResponseDto;
import com.book_tracker.book.application.exception.BookNotFoundException;
import com.book_tracker.book.application.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleBookNotFoundException_shouldReturnNotFoundStatus() {
        BookNotFoundException ex = new BookNotFoundException();
        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleBookNotFoundException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Book not found", Objects.requireNonNull(response.getBody()).message());
    }

    @Test
    void handleGeneralException_shouldReturnBadRequestStatus() {
        Exception ex = new Exception();
        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleGeneralException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Something went wrong", Objects.requireNonNull(response.getBody()).message());
    }

    @Test
    void handleValidationException_shouldReturnBadRequestStatusWithMessage() {
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(mock(BindingResult.class));
        when(ex.getBindingResult().getAllErrors()).thenReturn(List.of(mock(ObjectError.class)));
        when(ex.getBindingResult().getAllErrors().getFirst().getDefaultMessage()).thenReturn("Validation error");

        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleValidationException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation error", Objects.requireNonNull(response.getBody()).message());
    }

    @Test
    void handleTypeMismatchException_shouldReturnBadRequestStatusWithMessage() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getValue()).thenReturn("invalidValue");
        when(ex.getRequiredType()).thenReturn((Class) Integer.class);
        when(ex.getMessage()).thenReturn("Type mismatch");

        ResponseEntity<ErrorResponseDto> response = globalExceptionHandler.handleTypeMismatchException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Failed to convert value of type 'String' to required type 'Integer'; Type mismatch", Objects.requireNonNull(response.getBody()).message());
    }
}