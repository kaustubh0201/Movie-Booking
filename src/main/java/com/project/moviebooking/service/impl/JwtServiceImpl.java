package com.project.moviebooking.service.impl;

import com.project.moviebooking.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Service implementation for JWT (JSON Web Token) generation and handling.
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey; // Secret key for JWT signing.

    @Value(value = "${application.security.jwt.expiration}")
    private long accessTokenValidity; // Validity duration for access tokens.

    @Value(value = "${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenValidity; // Validity duration for refresh tokens.

    @Autowired
    private UserDetailsService userDetailsService; // Service for user details retrieval.

    /**
     * Initializes the service by encoding the secret key.
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Generates an access token based on the provided username and roles.
     *
     * @param username The username for which the token is generated.
     * @param roles    The list of roles associated with the user.
     * @return A generated access token.
     */
    @Override
    public String generateAccessToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        return generateToken(claims, username, accessTokenValidity);
    }

    /**
     * Generates a refresh token based on the provided username and roles.
     *
     * @param username The username for which the token is generated.
     * @param roles    The list of roles associated with the user.
     * @return A generated refresh token.
     */
    @Override
    public String generateRefreshToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        return generateToken(claims, username, refreshTokenValidity);
    }

    /**
     * Generates a token with the provided claims, username, and validity duration.
     *
     * @param claims   The claims to be included in the token.
     * @param username The username associated with the token.
     * @param validity The validity duration of the token.
     * @return The generated token.
     */
    private String generateToken(Map<String, Object> claims, String username, long validity) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Resolves the token from the HTTP request's authorization header.
     *
     * @param request The HTTP servlet request containing the token.
     * @return The resolved token from the request.
     */
    @Override
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }

        return null;
    }

    /**
     * Retrieves authentication details from the given token.
     *
     * @param token The token from which authentication details are extracted.
     * @return Authentication details derived from the token.
     */
    @Override
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(extractUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * Checks if the given token is valid for the specified user.
     *
     * @param token The token to be validated.
     * @param user  The user for whom the token is being validated.
     * @return True if the token is valid for the user, otherwise false.
     */
    @Override
    public boolean isTokenValid(String token, String user) {
        final String username = extractUsername(token);
        return (username.equals(user) && !isTokenExpired(token));
    }

    /**
     * Checks if the given token has expired.
     *
     * @param token The token to be checked for expiration.
     * @return True if the token is expired, otherwise false.
     */
    @Override
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the username from the provided token.
     *
     * @param token The token from which the username is extracted.
     * @return The extracted username.
     */
    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the provided token.
     *
     * @param token The token from which the expiration date is extracted.
     * @return The expiration date of the token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the token using a claim resolver.
     *
     * @param token         The token from which the claim is extracted.
     * @param claimResolver The function used to resolve the desired claim.
     * @param <T>           The type of the extracted claim.
     * @return The resolved claim from the token.
     */
    private  <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extracts all claims from the provided token.
     *
     * @param token The token from which claims are extracted.
     * @return All extracted claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Retrieves the signing key used for JWT token verification.
     *
     * @return The signing key for token verification.
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
