package org.example.ecommerce.common;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Instant createdAt;
    private Instant updatedAt;
    private Boolean deleted;

    @PrePersist
    public void updateCreatedAtAndUpdatedAt() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
        deleted = false;
    }

    @PreUpdate
    public void updateUpdatedAt() {
        updatedAt = Instant.now();
    }

}
