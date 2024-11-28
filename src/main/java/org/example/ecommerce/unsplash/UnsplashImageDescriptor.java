package org.example.ecommerce.unsplash;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record UnsplashImageDescriptor(String id,
                                      @JsonProperty("created_at") Instant createdAt,
                                      String description,
                                      UrlDescriptor urls) {
    public record UrlDescriptor(String raw, String regular) {
    }
}