package com.dgnklz.hrmanagementsystem.controllers;

import com.dgnklz.hrmanagementsystem.cores.exceptions.messages.ResponseMessage;
import com.dgnklz.hrmanagementsystem.models.securities.User;
import com.dgnklz.hrmanagementsystem.models.securities.UserDetailsImpl;
import com.dgnklz.hrmanagementsystem.models.securities.UserERole;
import com.dgnklz.hrmanagementsystem.models.securities.Userrole;
import com.dgnklz.hrmanagementsystem.repositories.UserRepository;
import com.dgnklz.hrmanagementsystem.repositories.UserRoleRepository;
import com.dgnklz.hrmanagementsystem.services.abstracts.TokenService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SigninResponse;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// http://localhost:8080/api/auth/signup
// http://localhost:8080/api/auth/signin
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    PasswordEncoder encoder;
    TokenService tokenService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage("Username is already taken!"));
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ResponseMessage("Email is already in use!"));
        }

        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        Set<String> strRoles = request.getRole();
        Set<Userrole> roles = new HashSet<>();

        if (strRoles == null) {
            Userrole userrole = userRoleRepository.findByName(UserERole.ROLE_MODERATOR);
            roles.add(userrole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Userrole adminRole = userRoleRepository.findByName(UserERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    default:
                        Userrole userRole = userRoleRepository.findByName(UserERole.ROLE_MODERATOR);
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);

        userRepository.save(user);

        return ResponseEntity.ok(new ResponseMessage("User registered successfully !"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody SigninRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String token = tokenService.generateToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new SigninResponse(token,
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }
}