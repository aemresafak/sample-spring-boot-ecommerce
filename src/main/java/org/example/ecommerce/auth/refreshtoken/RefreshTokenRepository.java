package org.example.ecommerce.auth.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
    Optional<RefreshTokenEntity> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE RefreshTokenEntity r SET r.deleted = true WHERE r.id = :id")
    int markAsDeleted(UUID id);
}
