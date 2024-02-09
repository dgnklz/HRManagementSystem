package com.dgnklz.hrmanagementsystem.services.conceretes;

import com.dgnklz.hrmanagementsystem.cores.exceptions.messages.ResponseMessage;
import com.dgnklz.hrmanagementsystem.cores.mapping.ModelMapperService;
import com.dgnklz.hrmanagementsystem.models.securities.User;
import com.dgnklz.hrmanagementsystem.models.securities.UserDetailsImpl;
import com.dgnklz.hrmanagementsystem.repositories.UserRepository;
import com.dgnklz.hrmanagementsystem.services.abstracts.TokenService;
import com.dgnklz.hrmanagementsystem.services.abstracts.UserService;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SigninRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.requests.security.SignupRequest;
import com.dgnklz.hrmanagementsystem.services.payloads.responses.security.SigninResponse;
import com.dgnklz.hrmanagementsystem.services.rules.AuthBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    PasswordEncoder encoder;
    TokenService tokenService;
    AuthBusinessRules rule;
    ModelMapperService mapper;

    @Override
    public ResponseEntity<?> signup(SignupRequest request) {
        rule.checkIfUserExistsByUsername(request.getUsername());
        rule.checkIfUserExistsByEmail(request.getEmail());

        User user = mapper.forRequest().map(request, User.class);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRoles(rule.setUserRole(request.getRole()));

        userRepository.save(user);

        return ResponseEntity.ok(new ResponseMessage("User registered successfully !"));
    }

    @Override
    public ResponseEntity<?> signin(SigninRequest request) {
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

    @Override
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout successful");
    }
}
