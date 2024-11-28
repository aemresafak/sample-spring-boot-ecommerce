package org.example.ecommerce.productprice;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;
import org.example.ecommerce.currency.CurrencyEntity;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "product_price")
@Getter
@Setter
public class ProductPriceEntity extends BaseEntity {
    private BigDecimal price;
    private BigDecimal discountPercentage;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;
    private UUID productId;
}
