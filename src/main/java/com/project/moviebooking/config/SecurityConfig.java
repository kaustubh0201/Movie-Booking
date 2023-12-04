package com.project.moviebooking.config;

import com.project.moviebooking.component.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Configuration class responsible for setting up and configuring security measures,
 * including authentication and authorization, within the movie booking application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * JwtTokenFilter instance responsible for filtering and processing JWT token for authentication
     * and authorization purposes.
     */
    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    /**
     * AuthenticationProvider instance for handling authentication within the security configuration.
     */
    @Autowired
    private AuthenticationProvider authenticationProvider;

    /**
     * Configures the security filter chain defining security rules for different endpoints.
     *
     * @param httpSecurity HttpSecurity instance to configure security settings.
     * @return SecurityFilterChain configured with defined security rules.
     * @throws Exception If there is an error during security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/v2/api-docs/**", "/v3/api-docs",
                        "/swagger-resources/**", "/webjars/**", "/swagger.json", "/swagger-ui.html",
                        "/v3/api-docs.yaml").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
