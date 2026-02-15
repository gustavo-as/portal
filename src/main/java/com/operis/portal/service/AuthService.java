package com.operis.portal.service;

import com.operis.portal.dto.*;
import com.operis.portal.model.User;
import com.operis.portal.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    // Chave secreta JWT - para dev, use application.properties
    private final String JWT_SECRET = "secret-key-portal";
    private final Long JWT_EXPIRATION = 3600L; // 1 hora

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty() || !passwordEncoder.matches(request.getPassword(), userOpt.get().getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }

        User user = userOpt.get();
        String token = generateToken(user);

        // Para simplicidade, refreshToken é UUID
        String refreshToken = UUID.randomUUID().toString();

        return LoginResponseDTO.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .expiresIn(JWT_EXPIRATION)
                .build();
    }

    public LoginResponseDTO refreshToken(RefreshTokenDTO request) {
        // TODO: implementar persistência de refresh token no banco
        // Por enquanto, apenas retorna um novo access token
        throw new UnsupportedOperationException("Refresh token not implemented yet");
    }

    private String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + JWT_EXPIRATION * 1000);

        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("superAdmin", user.isSuperAdmin())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                .compact();
    }
}
