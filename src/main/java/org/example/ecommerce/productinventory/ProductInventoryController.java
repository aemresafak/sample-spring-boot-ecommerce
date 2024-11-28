package org.example.ecommerce.productinventory;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/inventories")
@RequiredArgsConstructor
public class ProductInventoryController {
    private final ProductInventoryJpaService productInventoryJpaService;

    @GetMapping("/{productId}")
    public ProductInventory getProductInventory(@PathVariable UUID productId) {
        return productInventoryJpaService.findById(productId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public void createProductInventory(@RequestBody ProductInventory productInventory) {
        productInventoryJpaService.createOrUpdateInventory(productInventory.productId(), productInventory.quantity());
    }
}
