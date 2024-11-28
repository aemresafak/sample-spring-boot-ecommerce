package org.example.ecommerce.subcategory.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.Nullable;

import java.util.UUID;

public record CreateSubCategoryRequest(@NotBlank String name, @Nullable String description, @NotNull UUID categoryId) {
}
