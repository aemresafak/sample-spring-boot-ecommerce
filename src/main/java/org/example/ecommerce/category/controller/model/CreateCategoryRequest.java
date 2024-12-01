package org.example.ecommerce.category.controller.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public record CreateCategoryRequest(@NotBlank
                                    @Size(max = 255)
                                    String name, @Nullable String description) {
}
