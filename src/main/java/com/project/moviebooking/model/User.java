package com.project.moviebooking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents the model for storing information about a user.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User implements UserDetails {

    /**
     * Represents the unique identifier of the user.
     */
    @Id
    private String userId;

    /**
     * Represents the name of the user.
     */
    private String name;

    /**
     * Represents the username of the user (indexed and unique).
     */
    @Indexed(unique = true)
    private String username;

    /**
     * Represents the email address of the user (indexed and unique).
     */
    @Indexed(unique = true)
    private String emailId;

    /**
     * Represents the password of the user.
     */
    private String password;

    /**
     * Represents the roles assigned to the user.
     */
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    /**
     * Indicates if the user is verified.
     */
    private boolean isVerified;

    /**
     * Represents the one-time password for user verification.
     */
    private String otp;

    // Override UserDetails interface methods

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
