package org.example.ecommerce.unsplash;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface UnsplashRestService {
    @GetExchange("/search/photos")
    UnsplashSearchResult searchPhotos(@RequestParam String query);
}
