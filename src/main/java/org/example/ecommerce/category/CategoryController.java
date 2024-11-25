package org.example.ecommerce.category;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.common.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryJpaService categoryJpaService;

    @GetMapping
    public List<Category> findAll() {
        return categoryJpaService.findAll();
    }

    @PostMapping
    public CreateCategoryResponse createCategory(@RequestBody CreateCategoryRequest requestBody) {
        var id = categoryJpaService.createCategory(requestBody.name(), requestBody.description());
        return new CreateCategoryResponse(id);
    }

    @GetMapping("/{categoryId}")
    public Category findCategoryById(@PathVariable Integer categoryId) {
        return categoryJpaService
                .findById(categoryId)
                .orElseThrow(NotFoundException::new);
    }


}
