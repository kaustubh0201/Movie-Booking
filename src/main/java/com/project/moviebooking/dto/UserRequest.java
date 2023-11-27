package com.project.moviebooking.dto;


import com.project.moviebooking.constraint.EmailIdConstraint;
import com.project.moviebooking.constraint.NameConstraint;
import com.project.moviebooking.constraint.PasswordConstraint;
import com.project.moviebooking.constraint.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NameConstraint
    private String name;

    @UsernameConstraint
    private String username;

    @EmailIdConstraint
    private String emailId;

    @PasswordConstraint
    private String password;

}
