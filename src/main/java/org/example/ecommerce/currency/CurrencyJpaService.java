package org.example.ecommerce.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CurrencyJpaService {
    private final CurrencyRepository currencyRepository;

    public Optional<Currency> getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code)
                .map(Currency::from);
    }

    public Optional<Currency> getCurrencyById(UUID id) {
        return currencyRepository.findById(id)
                .map(Currency::from);
    }
}
