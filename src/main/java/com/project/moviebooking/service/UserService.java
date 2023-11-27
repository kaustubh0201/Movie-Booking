package com.project.moviebooking.service;

import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.model.User;
import com.project.moviebooking.repository.UserRepository;
import com.project.moviebooking.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    public void createUser(UserRequest userRequest) {

        User user = utils.userRequestToUserTransformer(userRequest);

        userRepository.save(user);
        log.info("User added to the database with {}", user.getUserId());

    }

}
