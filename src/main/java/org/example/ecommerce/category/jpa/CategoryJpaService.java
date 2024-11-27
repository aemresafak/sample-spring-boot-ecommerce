package org.example.ecommerce.category.jpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.category.Category;
import org.example.ecommerce.common.NotFoundException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryJpaService {
    private final CategoryRepository categoryRepository;

    public UUID createCategory(String name, @Nullable String description) {
        log.info("Creating category with {} {}", kv("name", name), kv("description", description));
        var category = new CategoryEntity();
        category.setName(name);
        category.setDescription(description);
        var savedCategory = categoryRepository.save(category);
        return savedCategory.getId();
    }

    public void updateDescription(UUID categoryId, @Nullable String description) {
        log.info("Updating description {} {}", kv("categoryId", categoryId), kv("description", description));
        var affectedRows = categoryRepository.updateCategoryDescription(categoryId, description);
        if (affectedRows == 0) {
            throw new NotFoundException();
        }
    }

    public List<Category> findAll() {
        log.info("Finding all categories");
        return categoryRepository.findAll().stream().map(Category::from).toList();
    }

    public Optional<Category> findById(UUID id) {
        log.info("Fetching category by {}", kv("id", id));
        return categoryRepository.findById(id).map(Category::from);
    }

}
