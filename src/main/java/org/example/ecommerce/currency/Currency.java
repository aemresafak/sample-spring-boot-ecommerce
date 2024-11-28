package org.example.ecommerce.currency;

import java.util.UUID;

public record Currency(UUID id, String code, String name, String symbol) {
    public static Currency from(CurrencyEntity entity) {
        return new Currency(entity.getId(), entity.getCode(), entity.getName(), entity.getSymbol());
    }
}
