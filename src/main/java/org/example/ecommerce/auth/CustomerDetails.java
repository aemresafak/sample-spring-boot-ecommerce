package org.example.ecommerce.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDetails(@NotBlank String firstName, @NotBlank String lastName, @NotNull LocalDate birthDate) {
}