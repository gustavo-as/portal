package com.operis.portal.control;

import com.operis.portal.dto.*;
import com.operis.portal.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthControl {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
//        return ResponseEntity.ok(authService.login(request));
        return ResponseEntity.ok(LoginResponseDTO.builder().build());
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(@RequestBody RefreshTokenDTO request) {
//        return ResponseEntity.ok(authService.refreshToken(request));
        return ResponseEntity.ok(LoginResponseDTO.builder().build());
    }
}
