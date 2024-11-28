package org.example.ecommerce.currency;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.example.ecommerce.common.BaseEntity;

@Entity
@Table(name = "currency")
@Getter
@Setter
public class CurrencyEntity extends BaseEntity {
    private String code;
    private String name;
    private String symbol;
}
