package org.example.ecommerce.imagegenerator;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.BusinessException;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.unsplash.UnsplashRestService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

@RequiredArgsConstructor
@Service
public class ImageGeneratorService {
    private final UnsplashRestService unsplashRestService;

    public ImageDescriptor generateImage(String description) {
        try {
            var result = unsplashRestService.searchPhotos(description);
            var unsplashImageDetails = result.unsplashImageDetails();
            if (unsplashImageDetails.isEmpty()) {
                throw new NotFoundException();
            }
            var imageUrl = unsplashImageDetails.getFirst().urls().regular();
            var unsplashDescription = unsplashImageDetails.getFirst().description();
            return new ImageDescriptor(unsplashDescription, imageUrl);
        } catch (RestClientException ex) {
            throw new BusinessException();
        }
    }
}
