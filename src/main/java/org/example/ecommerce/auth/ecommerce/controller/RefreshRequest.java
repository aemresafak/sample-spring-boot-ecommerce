package org.example.ecommerce.auth.ecommerce.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

record RefreshRequest(
        @NotNull UUID tokenId, @NotBlank String refreshToken) {
}
