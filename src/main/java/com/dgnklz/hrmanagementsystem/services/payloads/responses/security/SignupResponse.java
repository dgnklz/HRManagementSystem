package com.dgnklz.hrmanagementsystem.services.payloads.responses.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupResponse {
    private String firstname;
    private String lastname;
    private String email;
    private List<String> roles;

    private String message;
}