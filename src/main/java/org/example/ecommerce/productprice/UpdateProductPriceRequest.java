package org.example.ecommerce.productprice;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.UUID;

public record UpdateProductPriceRequest(@PositiveOrZero @Digits(integer = 10, fraction = 2) BigDecimal price,
                                        @PositiveOrZero @Digits(integer = 1, fraction = 2) BigDecimal discountPercentage,
                                        @Nullable String currencyCode,
                                        @NotNull UUID priceId) {
}
