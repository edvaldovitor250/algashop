package com.algaworks.algashop.product.catalog.presentation;

import com.algaworks.algashop.product.catalog.application.PageModel;
import com.algaworks.algashop.product.catalog.application.category.management.CategoryInput;
import com.algaworks.algashop.product.catalog.application.category.management.CategoryManagementApplicationService;
import com.algaworks.algashop.product.catalog.application.category.query.CategoryDetailOutput;
import com.algaworks.algashop.product.catalog.application.category.query.CategoryFilter;
import com.algaworks.algashop.product.catalog.application.category.query.CategoryQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoryController {

    private final CategoryQueryService categoryQueryService;
    private final CategoryManagementApplicationService categoryManagementApplicationService;

    @GetMapping
    public PageModel<CategoryDetailOutput> filter(CategoryFilter filter, WebRequest webRequest) {
        OffsetDateTime lastModifiedAt = categoryQueryService.lastModifiedAt();

        if(webRequest.checkNotModified(lastModifiedAt.toEpochSecond())) {
            return ResponseEntity.notModified()
                    .cacheControl(CacheControl.maxAge(Duration.ofMinutes(10)))
                    .build();
        }

        PageModel<CategoryDetailOutput> pageModel = categoryQueryService.filter(filter);
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofMinutes(10)))
                .body(pageModel);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDetailOutput create(@RequestBody @Valid CategoryInput input) {
        UUID categoryId = categoryManagementApplicationService.create(input);
        return categoryQueryService.findById(categoryId);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDetailOutput> findById(@PathVariable UUID categoryId) {
        return ResponseEntity.ok
                .cacheControl(CacheControl.maxAge(Duration.ofMinutes(10)))
                .body(categoryQueryService.findById(categoryId));
    }

    @PutMapping("/{categoryId}")
    public CategoryDetailOutput update(
            @PathVariable UUID categoryId,
            @RequestBody @Valid CategoryInput input) {
        categoryManagementApplicationService.update(categoryId, input);
        return categoryQueryService.findById(categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable UUID categoryId) {
        categoryManagementApplicationService.disable(categoryId);
    }
}