package org.example.ecommerce.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class CustomerEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
