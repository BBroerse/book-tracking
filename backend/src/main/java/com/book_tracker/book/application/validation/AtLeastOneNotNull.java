package com.book_tracker.book.application.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneNotNullValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotNull {
    String message() default "Either 'isbn' or 'title' must be provided";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
