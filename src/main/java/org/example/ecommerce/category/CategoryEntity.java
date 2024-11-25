package org.example.ecommerce.category;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
public class CategoryEntity extends BaseEntity<Integer> {
    private String name;
    @Nullable
    private String description;
}
