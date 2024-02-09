package com.dgnklz.hrmanagementsystem.services.payloads.responses.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SigninResponse {
    private String token;
    private String username;
    private String email;
    private List<String> roles;
}