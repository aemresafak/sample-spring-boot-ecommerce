package org.example.ecommerce.category;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;
import org.springframework.lang.Nullable;

@Entity
@Getter
@Setter
@Table(name ="category")
public class CategoryEntity extends BaseEntity {
    private String name;
    @Nullable
    private String description;
}
