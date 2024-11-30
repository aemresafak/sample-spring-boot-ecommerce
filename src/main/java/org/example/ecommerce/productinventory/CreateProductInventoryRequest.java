package org.example.ecommerce.productinventory;

import jakarta.validation.constraints.PositiveOrZero;

public record CreateProductInventoryRequest(@PositiveOrZero long quantity) {
}
