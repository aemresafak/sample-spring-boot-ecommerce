package org.example.ecommerce.productprice;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/prices")
@RequiredArgsConstructor
public class ProductPriceController {
    private final ProductPriceJpaService productPriceJpaService;

    @GetMapping("/{productId}")
    public ProductPrice getProductPrice(@PathVariable UUID productId) {
        return productPriceJpaService.getProductPriceByProductId(productId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public UUID createProductPrice(@RequestBody CreateProductPriceRequest request) {
        return productPriceJpaService.createProductPrice(request.productId(), request.price(), request.discountPercentage(), request.currencyCode());
    }

    @PutMapping
    public ProductPrice updateProductPrice(@RequestBody UpdateProductPriceRequest request) {
        return productPriceJpaService.updateProductPrice(request.productId(), request.price(), request.discountPercentage(), request.currencyCode());
    }


}
