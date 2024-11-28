package org.example.ecommerce.product.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;
import org.example.ecommerce.subcategory.jpa.SubCategoryEntity;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "product")
@Getter
@Setter
public class ProductEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sub_category_id")
    private SubCategoryEntity subCategoryEntity;

    private String name;
    @Nullable
    private String description;
    @Nullable
    private String imageUrl;
}
