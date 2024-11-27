package org.example.ecommerce.category.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    @Transactional
    @Modifying
    @Query("""
            UPDATE CategoryEntity c SET c.description = :description, c.updatedAt = current_timestamp WHERE c.id =:categoryId AND c.deleted = FALSE
            """)
    int updateCategoryDescription(UUID categoryId, @Nullable String description);
}
