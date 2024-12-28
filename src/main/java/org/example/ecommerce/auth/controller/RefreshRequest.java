package org.example.ecommerce.auth.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RefreshRequest(
        @NotNull UUID tokenId, @NotBlank String refreshToken) {
}
