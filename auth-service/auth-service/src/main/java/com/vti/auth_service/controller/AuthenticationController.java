package com.vti.auth_service.controller;

import com.vti.auth_service.dto.request.LoginRequestDto;
import com.vti.auth_service.dto.request.RegisterRequestDto;
import com.vti.auth_service.dto.response.AuthenticationResponseDto;
import com.vti.auth_service.dto.response.RegisterResponseDto;
import com.vti.auth_service.exception.CustomException;
import com.vti.auth_service.user.services.AuthenticationService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {
    public final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto registerRequest) throws Exception {
        RegisterResponseDto registerResponse = authenticationService.register(registerRequest);
        return ResponseEntity
                .status(registerResponse.getStatus())
                .body(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody @Valid LoginRequestDto loginRequest) throws Exception {
        log.info("Password: {}", loginRequest.getPassword());
        log.info("Username: {}", loginRequest.getUsername());

        AuthenticationResponseDto authenticationResponse = authenticationService.login(loginRequest);
        return ResponseEntity
                .status(authenticationResponse.getStatus())
                .body(authenticationResponse);
    }

    @PostMapping("/refresh-token")
    public AuthenticationResponseDto refreshToken(
            @RequestHeader("Authorization") String authHeader)
            throws ValidationException, CustomException {

        return authenticationService.refreshToken(authHeader);
    }
}
