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

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Field cannot be empty")
@Pattern(regexp = "^(?!\\.)[a-zA-Z0-9._%+-]+(?<!\\.)@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
public @interface EmailIdConstraint {

    String message() default "Invalid emailID";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
