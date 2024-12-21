package org.example.ecommerce.imagegenerator;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/images")
@PreAuthorize(value = "hasRole('ADMIN')")
@RequiredArgsConstructor
public class ImageGeneratorController {
    private final ImageGeneratorService imageGeneratorService;

    @GetMapping
    public ImageDescriptor generateImage(@RequestParam @NotBlank String description) {
        return imageGeneratorService.generateImage(description);
    }
}
