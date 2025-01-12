package org.example.ecommerce.auth.ecommerce.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerDetailsDTO(@NotBlank String firstName, @NotBlank String lastName, @NotNull LocalDate birthDate) {
}