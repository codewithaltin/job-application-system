package com.example.careerify.controller;


import com.example.careerify.common.dto.JwtAuthenticationResponseDTO;
import com.example.careerify.common.dto.SignInRequestDTO;
import com.example.careerify.common.dto.SignUpRequestDTO;
import com.example.careerify.common.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/sign-up")
    public JwtAuthenticationResponseDTO signUp(@RequestBody SignUpRequestDTO request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponseDTO signIn(@RequestBody SignInRequestDTO request) {
        return authenticationService.signIn(request);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JwtAuthenticationResponseDTO> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken));
    }
}