package com.project.moviebooking.controller;

import com.project.moviebooking.model.User;
import com.project.moviebooking.repository.UserRepository;
import com.project.moviebooking.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a controller class for getting information of the user.
 * The API requests for user are declared here.
 * The methods for getting the information and related activities are present here.
 */
@Controller
@RequestMapping("/api")
@Slf4j
public class UserController {

    /**
     * This object is for accessing the functions of the user service layer.
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * This method is used for getting the information about the logged-in user.
     * @param userDetails Contains the information about the logged-in user.
     * @return An object is returned containing the information about the user.
     */
    @GetMapping("/currentUser")
    public ResponseEntity<Map<String, Object>> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<String, Object> response = new HashMap<>();

        try {
            User user = userService.getCurrentUser(userDetails.getUsername());
            response.put("currentUser", user);
            response.put("message", "Successfully got the value of the current user.");

            log.info("Successfully fetched the information of user: {}", user.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("errorMessage", e.getMessage());

            log.error("Error while fetching information of user: {} with error: {}", userDetails.getUsername(),
                    e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
