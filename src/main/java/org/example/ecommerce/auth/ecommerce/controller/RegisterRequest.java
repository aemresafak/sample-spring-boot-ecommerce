package org.example.ecommerce.auth.ecommerce.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

record RegisterRequest(@NotNull @Email String email,
                              @NotBlank String password,
                       @Valid @Nullable CustomerDetailsDTO customerDetailsDTO) {

}
