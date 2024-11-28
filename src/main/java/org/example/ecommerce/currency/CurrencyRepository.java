package org.example.ecommerce.currency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {
    Optional<CurrencyEntity> findByCode(String code);
}
