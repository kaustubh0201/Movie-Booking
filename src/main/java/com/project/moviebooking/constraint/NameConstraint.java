package com.project.moviebooking.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom validation constraint for verifying the validity of a user's name.
 * Checks if the provided string adheres to the specified name pattern and is not blank.
 */
@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Field cannot be empty")
@Pattern(regexp = "^[A-Za-z]+(?:\\s+[A-Za-z]+)*$")
public @interface NameConstraint {

    /**
     * Defines the error message when the name validation fails.
     */
    String message() default "Invalid name";
    /**
     * Groups related to validation. Unused in this constraint.
     */
    Class<?>[] groups() default {};
    /**
     * Payload for validation. Unused in this constraint.
     */
    Class<? extends Payload>[] payload() default {};
}
