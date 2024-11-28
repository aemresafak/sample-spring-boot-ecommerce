package org.example.ecommerce.productprice;

import org.example.ecommerce.currency.Currency;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPrice(UUID id, UUID productId, BigDecimal price, Currency currency,
                           BigDecimal discountPercentage) {
    public static ProductPrice from(ProductPriceEntity entity) {
        return new ProductPrice(entity.getId(), entity.getProductId(), entity.getPrice(), Currency.from(entity.getCurrency()), entity.getDiscountPercentage());
    }
}
