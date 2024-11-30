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

    @GetMapping("/{inventoryId}")
    public ProductInventory getProductInventory(@PathVariable UUID inventoryId) {
        return productInventoryJpaService.findById(inventoryId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public CreateProductInventoryResponse createProductInventory(@RequestBody CreateProductInventoryRequest request) {
        var id = productInventoryJpaService.createOrUpdateInventory(request.quantity());
        return new CreateProductInventoryResponse(id);
    }
}
