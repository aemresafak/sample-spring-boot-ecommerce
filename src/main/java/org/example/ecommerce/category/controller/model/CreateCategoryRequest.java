package org.example.ecommerce.category.controller.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.lang.Nullable;

public record CreateCategoryRequest(@NotBlank String name, @Nullable String description) {
}
