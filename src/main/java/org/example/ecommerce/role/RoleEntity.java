package org.example.ecommerce.role;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;

@Entity
@Getter
@Setter
@Table(name = "role")
public class RoleEntity extends BaseEntity {
    private String name;
}
