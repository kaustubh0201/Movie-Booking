package com.project.moviebooking.dto;

import com.project.moviebooking.constraint.EmailIdConstraint;
import com.project.moviebooking.constraint.NameConstraint;
import com.project.moviebooking.constraint.PasswordConstraint;
import com.project.moviebooking.constraint.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a request for user creation or update.
 * Contains fields for user details like name, username, email ID, and password.
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    /**
     * Represents the name of the user.
     */
    @NameConstraint
    private String name;

    /**
     * Represents the username of the user.
     */
    @UsernameConstraint
    private String username;

    /**
     * Represents the email ID of the user.
     */
    @EmailIdConstraint
    private String emailId;

    /**
     * Represents the password of the user.
     */
    @PasswordConstraint
    private String password;

}
