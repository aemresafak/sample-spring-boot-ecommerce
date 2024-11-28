package org.example.ecommerce.imagegenerator;

import org.springframework.lang.Nullable;

public record ImageDescriptor(@Nullable String description, String url) {
}
