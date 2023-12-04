package com.project.moviebooking.component;

import com.project.moviebooking.service.impl.JwtServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/**
 * JWT Token Filter is responsible for filtering incoming HTTP requests to validate and process JWT tokens
 * for authentication purposes.
 * Extends GenericFilterBean to provide servlet filter functionality.
 */
@Component
public class JwtTokenFilter extends GenericFilterBean {

    /**
     * The service responsible for JWT token operations, such as token resolution,
     * validation, and authentication.
     */
    @Autowired
    private JwtServiceImpl jwtService;

    /**
     * Filters incoming HTTP requests. Resolves JWT token, checks its validity,
     * retrieves authentication information from the token, and sets the authentication
     * in the security context if the token is valid.
     *
     * @param servletRequest The request to be processed.
     * @param servletResponse The response associated with the request.
     * @param filterChain The filter chain for invoking the next filter.
     * @throws IOException If an I/O error occurs during the filtering process.
     * @throws ServletException If an exception occurs in the filter chain.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        String token = jwtService.resolveToken((HttpServletRequest) servletRequest);

        if (token != null && !jwtService.isTokenExpired(token)) {
            Authentication authentication = jwtService.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
