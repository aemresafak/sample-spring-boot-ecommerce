package org.example.ecommerce.productprice;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.BusinessException;
import org.example.ecommerce.currency.CurrencyRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductPriceJpaService {
    private final ProductPriceRepository productPriceRepository;
    private final CurrencyRepository currencyRepository;

    public Optional<ProductPrice> getProductPriceById(UUID id) {
        return productPriceRepository.findById(id).map(ProductPrice::from);
    }

    @Transactional
    public ProductPrice updateProductPrice(UUID id, @Nullable BigDecimal price, @Nullable BigDecimal discountPercentage, @Nullable String currencyCode) {
        var productPriceEntityOptional = productPriceRepository.findById(id);
        if (productPriceEntityOptional.isEmpty()) {
            throw new BusinessException();
        }
        var oldProductPriceEntity = productPriceEntityOptional.get();
        var newProductPriceEntity = new ProductPriceEntity();
        newProductPriceEntity.setCurrency(oldProductPriceEntity.getCurrency());
        newProductPriceEntity.setPrice(oldProductPriceEntity.getPrice());
        newProductPriceEntity.setDiscountPercentage(oldProductPriceEntity.getDiscountPercentage());


        if (price != null) {
            newProductPriceEntity.setPrice(price);
        }

        if (discountPercentage != null) {
            newProductPriceEntity.setDiscountPercentage(discountPercentage);
        }

        if (currencyCode !=  null) {
            var currencyEntity = currencyRepository.findByCode(currencyCode).orElseThrow(BusinessException::new);
            newProductPriceEntity.setCurrency(currencyEntity);
        }

        var affectedRows = productPriceRepository.softDeleteById(oldProductPriceEntity.getId());

        if (affectedRows != 1) {
            throw new BusinessException();
        }

        var savedEntity = productPriceRepository.save(newProductPriceEntity);
        return ProductPrice.from(savedEntity);
    }

    public UUID createProductPrice(BigDecimal price, BigDecimal discountPercentage, String currencyCode) {
        var currencyEntity = currencyRepository.findByCode(currencyCode).orElseThrow(BusinessException::new);
        var productPriceEntity = new ProductPriceEntity();
        productPriceEntity.setCurrency(currencyEntity);
        productPriceEntity.setPrice(price);
        productPriceEntity.setDiscountPercentage(discountPercentage);
        var saved = productPriceRepository.save(productPriceEntity);
        return saved.getId();
    }
}
