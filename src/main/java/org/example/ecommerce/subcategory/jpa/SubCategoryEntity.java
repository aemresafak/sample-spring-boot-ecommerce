package org.example.ecommerce.subcategory.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.category.jpa.CategoryEntity;
import org.example.ecommerce.common.BaseEntity;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "sub_category")
@Getter
@Setter
public class SubCategoryEntity extends BaseEntity {
    private String name;
    @Nullable
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
