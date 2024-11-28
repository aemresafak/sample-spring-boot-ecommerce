package org.example.ecommerce.subcategory.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<SubCategoryEntity, UUID> {
}
