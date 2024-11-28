package org.example.ecommerce.productinventory;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;

@Entity
@Table(name = "product_inventory")
@Getter
@Setter
public class ProductInventoryEntity extends BaseEntity {
    private long quantity;
}
