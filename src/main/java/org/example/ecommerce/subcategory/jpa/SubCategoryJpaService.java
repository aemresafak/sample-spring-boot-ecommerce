package org.example.ecommerce.subcategory.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.category.jpa.CategoryRepository;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.subcategory.SubCategory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RequiredArgsConstructor
@Slf4j
@Service
public class SubCategoryJpaService {
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public Optional<SubCategory> findById(UUID id) {
        log.info("Fetching subcategory {}", kv("id", id));
        return subCategoryRepository.findById(id).map(SubCategory::from);
    }

    public List<SubCategory> findAll() {
        log.info("Finding all subcategories");
        return subCategoryRepository.findAll().stream().map(SubCategory::from).toList();
    }

    public UUID addSubCategory(String name, @Nullable String description, UUID categoryId) {
        try {
            var entity = new SubCategoryEntity();
            entity.setName(name);
            entity.setDescription(description);
            var category = categoryRepository.getReferenceById(categoryId);
            entity.setCategory(category);
            var savedEntity = subCategoryRepository.save(entity);
            return savedEntity.getId();
        } catch (DataIntegrityViolationException exception) {
            throw new NotFoundException("Category %s not found".formatted(categoryId));
        }

    }
}
