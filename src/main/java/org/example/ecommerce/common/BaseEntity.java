package org.example.ecommerce.common;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity<ID> {
    @Id
    private ID id;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;
}
