package org.example.ecommerce.productprice;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateProductPriceRequest(@PositiveOrZero @Digits(integer = 10, fraction = 2) BigDecimal price,
                                        @PositiveOrZero @Digits(integer = 1, fraction = 2) BigDecimal discountPercentage,
                                        @NotBlank String currencyCode) {
}
