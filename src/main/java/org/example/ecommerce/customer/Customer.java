package org.example.ecommerce.customer;

import java.time.LocalDate;
import java.util.UUID;

public record Customer(UUID id, String firstName, String lastName, LocalDate birthDate) {
    public static Customer from(CustomerEntity entity) {
        return new Customer(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getBirthDate());
    }
}
