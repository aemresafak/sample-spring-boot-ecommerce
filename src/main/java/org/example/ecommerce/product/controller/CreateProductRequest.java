package org.example.ecommerce.product.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public record CreateProductRequest(@NotBlank String name,
                                   @Nullable String description,
                                   @Nullable String imageUrl,
                                   @NotNull UUID subCategoryId) {
}
