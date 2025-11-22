package com.tastytown.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastytown.backend.dto.AuthRequest;
import com.tastytown.backend.dto.AuthResponse;
import com.tastytown.backend.dto.RefreshRequest;
import com.tastytown.backend.entity.User;
import com.tastytown.backend.repository.UserRepository;
import com.tastytown.backend.security.jwt.JwtUtils;
import com.tastytown.backend.service.IAuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final IAuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody AuthRequest request) {
        return new ResponseEntity<>(authService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest request) {
        String refreshToken = request.refreshToken(); 

        if (jwtUtils.validateToken(refreshToken, jwtUtils.getUsername(refreshToken))) {
            User user = userRepository.findByUserEmail(jwtUtils.getUsername(refreshToken))
                    .orElseThrow();
            String newAccessToken = jwtUtils.generateAccessToken(user, user.getRole().toString());
            String newRefreshToken = jwtUtils.generateRefreshToken(user); // optional rotation

            return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
