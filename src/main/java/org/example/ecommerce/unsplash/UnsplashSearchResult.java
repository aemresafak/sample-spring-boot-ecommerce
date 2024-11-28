package org.example.ecommerce.unsplash;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record UnsplashSearchResult(String total,
                                   @JsonProperty("total_pages") String totalPages,
                                   @JsonProperty("results") List<UnsplashImageDescriptor> unsplashImageDetails) {
}
