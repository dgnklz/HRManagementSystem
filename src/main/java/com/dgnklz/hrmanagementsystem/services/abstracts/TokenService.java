package com.dgnklz.hrmanagementsystem.services.abstracts;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {
    String generateToken(Authentication auth);
    String getUsername(String token);
    boolean validateToken(String token);
}