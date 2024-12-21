package org.example.ecommerce.subcategory.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.NotFoundException;
import org.example.ecommerce.subcategory.SubCategory;
import org.example.ecommerce.subcategory.jpa.SubCategoryJpaService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/sub-categories")
@PreAuthorize(value = "hasRole('ADMIN')")
public class SubCategoryRestController {
    private final SubCategoryJpaService subCategoryJpaService;

    @GetMapping
    public List<SubCategory> getAllSubCategories() {
        return subCategoryJpaService.findAll();
    }

    @GetMapping("/{subCategoryId}")
    public SubCategory getSubCategory(@PathVariable UUID subCategoryId) {
        return subCategoryJpaService.findById(subCategoryId).orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public CreateSubCategoryResponse createSubCategory(@Valid @RequestBody CreateSubCategoryRequest createSubCategoryRequest) {
        var id = subCategoryJpaService.addSubCategory(createSubCategoryRequest.name(), createSubCategoryRequest.description(), createSubCategoryRequest.categoryId());
        return new CreateSubCategoryResponse(id);
    }

}
