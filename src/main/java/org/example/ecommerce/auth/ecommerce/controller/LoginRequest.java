package org.example.ecommerce.auth.ecommerce.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(@Email @NotNull String email, @NotBlank String credentials) {
}
