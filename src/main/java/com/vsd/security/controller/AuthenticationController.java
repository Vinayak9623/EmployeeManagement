package com.vsd.security.controller;

import com.vsd.common.ApiResponse;
import com.vsd.entity.User;
import com.vsd.Repository.UserRepository;
import com.vsd.security.JwtService;
import com.vsd.security.dto.AuthRequest;
import com.vsd.security.dto.Authresponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Authresponse>> login(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String jwtToken = jwtService.generateToken(user);
            Authresponse authresponse = Authresponse.builder()
                    .token(jwtToken)
                    .build();

            ApiResponse<Authresponse> response = new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Login successful",
                    authresponse,
                    null
            );

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            ApiResponse<Authresponse> errorResponse = new ApiResponse<>(
                    HttpStatus.UNAUTHORIZED.value(),
                    "Invalid credentials",
                    null,
                    null
            );
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
