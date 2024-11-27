package org.example.ecommerce.category.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ecommerce.category.Category;
import org.example.ecommerce.category.controller.model.CreateCategoryRequest;
import org.example.ecommerce.category.controller.model.CreateCategoryResponse;
import org.example.ecommerce.category.controller.model.UpdateDescriptionRequest;
import org.example.ecommerce.category.jpa.CategoryJpaService;
import org.example.ecommerce.common.NotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
@Validated
public class CategoryController {

    private final CategoryJpaService categoryJpaService;

    @GetMapping
    public List<Category> findAll() {
        return categoryJpaService.findAll();
    }

    @PostMapping
    public CreateCategoryResponse createCategory(@RequestBody @Valid CreateCategoryRequest requestBody) {
        var id = categoryJpaService.createCategory(requestBody.name(), requestBody.description());
        return new CreateCategoryResponse(id);
    }


    @GetMapping("/{categoryId}")
    public Category findCategoryById(@PathVariable UUID categoryId) {
        return categoryJpaService
                .findById(categoryId)
                .orElseThrow(NotFoundException::new);
    }

    @PatchMapping("/{categoryId}/description")
    public void updateDescription(@PathVariable UUID categoryId, @Valid @RequestBody UpdateDescriptionRequest request) {
        categoryJpaService.updateDescription(categoryId, request.description());
    }
}
