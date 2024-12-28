package org.example.ecommerce.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.auth.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        var token = authenticationService.login(loginRequest.email(), loginRequest.credentials());
        return new LoginResponse(token.accessToken(), token.refreshTokenId(), token.refreshToken());
    }

    @PostMapping("/register")
    public void register(@Valid @RequestBody RegisterRequest request) {
        authenticationService.register(request.email(), request.password(), request.customerDetails());
    }

    @PostMapping("/refresh")
    public LoginResponse refresh(@Valid @RequestBody RefreshRequest refreshRequest) {
        var token = authenticationService.refresh(refreshRequest.tokenId(), refreshRequest.refreshToken());
        return new LoginResponse(token.accessToken(), token.refreshTokenId(), token.refreshToken());
    }
}
