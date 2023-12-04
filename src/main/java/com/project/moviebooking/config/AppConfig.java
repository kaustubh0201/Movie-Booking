package com.project.moviebooking.config;

import com.project.moviebooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class responsible for defining beans related to
 * authentication, user details, and password encoding within
 * the movie booking application.
 */
@Configuration
public class AppConfig {

    /**
     * Autowired UserRepository instance used to retrieve user details
     * for authentication and authorization purposes.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Creates and configures an instance of DaoAuthenticationProvider
     * to handle authentication based on the configured UserDetailsService
     * and PasswordEncoder.
     *
     * @return An instance of AuthenticationProvider for user authentication.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Defines a UserDetailsService bean that retrieves user details
     * by username from the UserRepository or throws an exception
     * if the user is not found.
     *
     * @return An implementation of UserDetailsService.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Creates a PasswordEncoder bean to encrypt and verify passwords
     * for user authentication and authorization.
     *
     * @return An instance of PasswordEncoder for password hashing.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines an AuthenticationManager bean required for authentication
     * configuration.
     *
     * @param configuration AuthenticationConfiguration instance for obtaining the AuthenticationManager.
     * @return An instance of AuthenticationManager.
     * @throws Exception If there is an error obtaining the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
