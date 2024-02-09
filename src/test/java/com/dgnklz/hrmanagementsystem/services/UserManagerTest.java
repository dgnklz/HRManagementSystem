package com.dgnklz.hrmanagementsystem.services;

import com.dgnklz.hrmanagementsystem.cores.exceptions.messages.ResponseMessage;
import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.mocks.MocksFacilitator;
import com.dgnklz.hrmanagementsystem.models.securities.User;
import com.dgnklz.hrmanagementsystem.models.securities.UserDetailsImpl;
import com.dgnklz.hrmanagementsystem.repositories.UserRepository;
import com.dgnklz.hrmanagementsystem.services.abstracts.TokenService;
import com.dgnklz.hrmanagementsystem.services.conceretes.UserManager;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.employee.GetEmployeeByIdResponse;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SigninResponse;
import com.dgnklz.hrmanagementsystem.services.rules.AuthBusinessRules;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserManagerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AuthBusinessRules authBusinessRules;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserManager userManager;

    @InjectMocks
    private MocksFacilitator mocks;

    @Mock
    Authentication auth;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        ModelMapper modelMapperMock = mock(ModelMapper.class);
        when(mapper.forRequest()).thenReturn(modelMapperMock);

        // Mocking authentication
         auth = mock(Authentication.class);
    }

    @Test
    public void testSignup_Success() {
        SignupRequest signupRequest = mocks.getSignupRequest();

        User user = mocks.getUserSecurity();

        ResponseEntity<ResponseMessage> responseEntityMock = ResponseEntity.ok(new ResponseMessage("User does not exist"));
        when(authBusinessRules.checkIfUserExistsByUsername(any())).thenAnswer(invocation -> responseEntityMock);
        when(authBusinessRules.checkIfUserExistsByEmail(any())).thenAnswer(invocation -> responseEntityMock);
        when(passwordEncoder.encode(any())).thenReturn("encodedPassword");


        when(mapper.forRequest().map(signupRequest, User.class)).thenReturn(user);

        ResponseEntity<?> responseEntity = userManager.signup(signupRequest);


        ResponseMessage expectedMessage = new ResponseMessage("User registered successfully !");
        ResponseMessage actualMessage = (ResponseMessage) responseEntity.getBody();
        assertEquals(expectedMessage.getMessage(), actualMessage.getMessage());
    }

    @Test
    public void testSignin_Success() {
        SigninRequest signinRequest = new SigninRequest();
        signinRequest.setUsername("testuser");
        signinRequest.setPassword("password");

        // Mocking authentication
        Authentication authentication = mock(Authentication.class);
        UserDetailsImpl userDetails = new UserDetailsImpl(1, "testuser", "test@example.com", "password", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
        when(authentication.getPrincipal()).thenReturn(userDetails);

        // Mocking authentication manager
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);

        ResponseEntity<?> responseEntity = userManager.signin(signinRequest);

        // Asserts
        Assertions.assertNotNull(responseEntity);
    }

    @Test
    public void testLogout_Success() {
        MockitoAnnotations.openMocks(this);

        ResponseEntity<?> responseEntity = userManager.logout();

        assertEquals(ResponseEntity.ok("Logout successful"), responseEntity);
    }
}