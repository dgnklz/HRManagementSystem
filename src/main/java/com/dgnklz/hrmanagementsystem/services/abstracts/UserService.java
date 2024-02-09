package com.dgnklz.hrmanagementsystem.services.abstracts;

import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> signup(SignupRequest request);
    ResponseEntity<?> signin(SigninRequest request);
    ResponseEntity<?> logout();
}
