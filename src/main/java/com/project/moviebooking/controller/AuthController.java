package com.project.moviebooking.controller;

import com.project.moviebooking.dto.AuthenticationRequest;
import com.project.moviebooking.dto.AuthenticationResponse;
import com.project.moviebooking.dto.UserRequest;
import com.project.moviebooking.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This is a controller class for authentication purposes.
 * The API requests for authentication activities are declared here.
 * The methods to authenticate the user, sign up the user and other related activities are present here.
 */
@Controller
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    /**
     * This object is for accessing the functions of the user service layer.
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * This method is used for signing up of the user. An OTP is sent to the email of the user which needs to be verified
     * in order to use the account.
     * @param userRequest Contains the information of the user that is required.
     * @return An object is returned containing the message if the user was successfully registered and otp was sent or not.
     * The message contains the error message in the case of any error.
     */
    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signup(@RequestBody UserRequest userRequest)  {
        return userService.createUser(userRequest);
    }

    /**
     * This method is used for verifying the email of the user, using the otp that was sent to the mail.
     * @param emailId This is the email of the user on which the OTP was sent.
     * @param otp This the OTP that was sent on the mail.
     * @return An object is returned containing the message if the user was successfully verified or not.
     * The message contains the error message in the case of any error.
     */
    @PatchMapping("/verify")
    public ResponseEntity<AuthenticationResponse> verifyUser(@RequestParam String emailId, @RequestParam String otp) {
        return userService.verifyUser(emailId, otp);
    }

    /**
     * This method is used for logging into the system.
     * @param data This contains the username and password of the user which is needed for the login process.
     * @return An object is returned with the access token and refresh token along with a message otherwise in the case of
     * error, an error message is returned.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest data) {
        return userService.login(data);
    }

    /**
     * This method is used for refreshing the access token which is done by using the refresh token.
     * @param request This is the request from which header is taken out in order to get the refresh token.
     * @param response This is the response that is used to interact with the HTTP response and sent back to the client.
     * @return An object is returned with the access token and the same refresh token along with a message otherwise
     * in the case of error, an error message is returned.
     */
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        return userService.refreshToken(request, response);
    }

    /**
     * This method is used in the case when the user forgets the password and requires to set a new password.
     * @param emailId This is the email of the user which will be used to send the OTP for changing the password.
     * @return An object is returned containing the message if the otp was sent or not for changing the password.
     * The message contains the error message in the case of any error.
     */
    @PatchMapping("/forget-password")
    public ResponseEntity<AuthenticationResponse> forgetPassword(@RequestParam String emailId) {
        return userService.forgotPassword(emailId);
    }

    /**
     * This method is used for verifying the OTP for the password change, if the OTP matches then the new password is
     * set.
     * @param emailId This is the email of the user who wants to change the password.
     * @param otp This is the OTP that is sent to the email for password change.
     * @param password This is the new password that the user wants to set.
     * @return An object is returned containing the message if the password was successfully changed or not. The message
     * contains the error message in the case of any error.
     */
    @PatchMapping("/forget-password-verify")
    public ResponseEntity<AuthenticationResponse> verifyOtpForgetPassword(@RequestParam String emailId,
                                                                          @RequestParam String otp,
                                                                          @RequestParam String password) {
        return userService.verifyForgetPassword(emailId, otp, password);
    }

    /**
     * This method is used for resending the OTP in the case of verifying the user when the use may not get the OTP mail.
     * @param emailId This is the mail on which the OTP needs to sent again for the verification of the user.
     * @return An object is returned containing the message if the otp was sent or not again for verification.
     * The message contains the error message in the case of any error.
     */
    @PatchMapping("/resend-otp")
    public ResponseEntity<AuthenticationResponse> resendOTP(@RequestParam String emailId) {
        return userService.sendOtpAgain(emailId);
    }
}
