package org.example.ecommerce.productprice;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.BusinessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductPriceJpaService {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceEntity getProductPriceByProductId(UUID productId) {
        return productPriceRepository.findByProductIdAndDeletedFalse(productId).orElseThrow(BusinessException::new);
    }
}
