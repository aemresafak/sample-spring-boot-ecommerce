package org.example.ecommerce.category;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;

@Entity
@Getter
@Setter
public class Category extends BaseEntity<Integer> {
    private String name;
    private String description;
}
