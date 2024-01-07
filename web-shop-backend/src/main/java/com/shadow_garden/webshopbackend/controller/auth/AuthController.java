package com.shadow_garden.webshopbackend.controller.auth;

import com.shadow_garden.webshopbackend.dto.auth.AuthenticationRequest;
import com.shadow_garden.webshopbackend.dto.auth.AuthenticationResponse;
import com.shadow_garden.webshopbackend.dto.auth.RefreshTokenRequest;
import com.shadow_garden.webshopbackend.dto.auth.RegisterRequest;
import com.shadow_garden.webshopbackend.exception.auth.RefreshTokenNotFoundException;
import com.shadow_garden.webshopbackend.exception.user.UserNotFoundException;
import com.shadow_garden.webshopbackend.service.auth.AuthenticationService;
import com.shadow_garden.webshopbackend.service.auth.JwtService;
import com.shadow_garden.webshopbackend.service.auth.RefreshTokenService;
import com.shadow_garden.webshopbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import com.shadow_garden.webshopbackend.entity.auth.RefreshToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(service.register(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody AuthenticationRequest request) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/validate_pass_token")
    public ResponseEntity validatePasswordResetToken(@RequestParam String token) {
        try {
            return ResponseEntity.ok(service.validatePasswordResetToken(token));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset_password")
    public ResponseEntity resetPassword(@RequestParam String password, @RequestParam String token) {
        try {
            return ResponseEntity.ok(service.resetPassword(password, token));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws RefreshTokenNotFoundException, UserNotFoundException {
        String refreshToken = refreshTokenRequest.getToken();
        var userRole = userService.getUserByUsername(refreshTokenRequest.getUsername());

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.generateToken(user);
                    return AuthenticationResponse
                            .builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshToken)
                            .username(refreshTokenRequest.getUsername())
                            .role(userRole.getRole())
                            .build();
                }).orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token not found"));
    }
}
