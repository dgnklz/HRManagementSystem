package com.dgnklz.hrmanagementsystem.controller;

import com.dgnklz.hrmanagementsystem.controllers.AuthController;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import com.dgnklz.hrmanagementsystem.services.abstracts.UserService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SigninResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SignupResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @InjectMocks
    private MocksFacilitator mocks;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignup() {
        SignupRequest signupRequest = mocks.getSignupRequest();
        SignupResponse signupResponse = mocks.getSignupResponse();

        when(userService.signup(any())).thenAnswer(invocation -> ResponseEntity.ok(signupResponse));

        ResponseEntity<?> responseEntity = authController.signup(signupRequest);

        assertEquals(signupResponse, responseEntity.getBody());
    }

    @Test
    public void testSignin() {
        SigninRequest signinRequest = mocks.getSigninRequest();
        SigninResponse signinResponse = mocks.getSigninResponse();

        when(userService.signin(any())).thenAnswer(invocation -> ResponseEntity.ok(signinResponse));

        ResponseEntity<?> responseEntity = authController.signin(signinRequest);

        assertEquals(signinResponse, responseEntity.getBody());
    }

    @Test
    public void testLogout() {
        when(userService.logout()).thenAnswer(invocation -> ResponseEntity.ok("Logout successful"));

        ResponseEntity<?> responseEntity = authController.logout();

        assertNotNull(responseEntity);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals("Logout successful", responseEntity.getBody());
    }
}