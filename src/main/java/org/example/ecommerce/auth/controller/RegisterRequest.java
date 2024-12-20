package org.example.ecommerce.auth.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.ecommerce.auth.CustomerDetails;
import org.springframework.lang.Nullable;

public record RegisterRequest(@NotNull @Email String email,
                              @NotBlank String password,
                              @Valid @Nullable CustomerDetails customerDetails) {

}
