package com.shadow_garden.webshopbackend.service.auth;

import com.shadow_garden.webshopbackend.dto.auth.AuthenticationRequest;
import com.shadow_garden.webshopbackend.dto.auth.AuthenticationResponse;
import com.shadow_garden.webshopbackend.dto.auth.RegisterRequest;
import com.shadow_garden.webshopbackend.entity.auth.PasswordResetTokenEntity;
import com.shadow_garden.webshopbackend.entity.auth.RefreshToken;
import com.shadow_garden.webshopbackend.entity.user.UserEntity;
import com.shadow_garden.webshopbackend.exception.auth.PasswordResetTokenIsExpiredException;
import com.shadow_garden.webshopbackend.exception.auth.PasswordResetTokenNotFoundException;
import com.shadow_garden.webshopbackend.exception.auth.UsernameIsOccupiedException;
import com.shadow_garden.webshopbackend.exception.user.UserNotFoundException;
import com.shadow_garden.webshopbackend.repository.auth.PasswordResetTokenRepository;
import com.shadow_garden.webshopbackend.repository.user.UserRepository;
import com.shadow_garden.webshopbackend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final String apiUrl = "https://localhost:8080";

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserService userService;

    public AuthenticationResponse register(RegisterRequest request) throws UsernameIsOccupiedException, UserNotFoundException {
        validateRegisterRequest(request);

        var userRole = request.getRole();

        var user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        repository.save(user);

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .username(user.getUsername())
                .role(userRole)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) throws UserNotFoundException {
        var userRole = userService.getUserByUsername(request.getUsername());


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = repository.findByUsername(request.getUsername()).orElseThrow(() -> new UserNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());

        return AuthenticationResponse
                .builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken.getToken())
                .username(user.getUsername())
                .role(userRole.getRole())
                .build();
    }

    public String resetPassword(String password, String token) throws PasswordResetTokenNotFoundException {
        PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(token).orElseThrow(() -> new PasswordResetTokenNotFoundException("Password reset token not found"));
        UserEntity userEntity = tokenEntity.getUser();

        userEntity.setPassword(passwordEncoder.encode(password));
        userEntity.setPasswordResetToken(null);
        repository.save(userEntity);

        passwordResetTokenRepository.delete(tokenEntity);

        return "Password reset succeeded";
    }

    private PasswordResetTokenEntity generatePasswordResetToken(UserEntity user) {
        return PasswordResetTokenEntity
                .builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(new Date(System.currentTimeMillis() + 1000 * 60 * 5))
                .build();
    }

    private void validateRegisterRequest(RegisterRequest request) throws UsernameIsOccupiedException {
        if (repository.findByUsername(request.getUsername()).isPresent())
            throw new UsernameIsOccupiedException("Account with this username is already registered");
    }

    public String validatePasswordResetToken(String token) throws PasswordResetTokenNotFoundException, PasswordResetTokenIsExpiredException {
        PasswordResetTokenEntity tokenEntity = passwordResetTokenRepository.findByToken(token).orElseThrow(() -> new PasswordResetTokenNotFoundException("Password reset token not found"));;

        ValidatePasswordResetToken(tokenEntity);

        return "Token validated successfully";
    }

    private void ValidatePasswordResetToken(PasswordResetTokenEntity token) throws PasswordResetTokenIsExpiredException {
        if (token.isExpired()) {
            passwordResetTokenRepository.delete(token);
            throw new PasswordResetTokenIsExpiredException("Password reset token is expired!");
        }
    }
}
