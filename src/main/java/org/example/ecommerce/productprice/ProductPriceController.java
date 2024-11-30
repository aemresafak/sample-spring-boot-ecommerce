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

    @GetMapping("/{priceId}")
    public ProductPrice getProductPrice(@PathVariable UUID priceId) {
        return productPriceJpaService.getProductPriceById(priceId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public UUID createProductPrice(@RequestBody CreateProductPriceRequest request) {
        return productPriceJpaService.createProductPrice(request.price(), request.discountPercentage(), request.currencyCode());
    }

    @PutMapping
    public ProductPrice updateProductPrice(@RequestBody UpdateProductPriceRequest request) {
        return productPriceJpaService.updateProductPrice(request.priceId(), request.price(), request.discountPercentage(), request.currencyCode());
    }


}
