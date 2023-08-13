package com.project.moviebooking.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z]+(?:\\s+[A-Za-z]+)*$")
    private String name;

    @NotBlank(message = "Username cannot be empty")
    @Pattern(regexp = "^[a-z_]{5,30}$")
    private String username;

    @NotBlank(message = "Email Id cannot be empty")
    @Pattern(regexp = "^(?!\\.)[a-zA-Z0-9._%+-]+(?<!\\.)@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String emailId;

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,20}$")
    private String password;
}
