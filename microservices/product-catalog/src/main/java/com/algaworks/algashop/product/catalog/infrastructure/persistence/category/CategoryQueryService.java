package com.algaworks.algashop.product.catalog.infrastructure.persistence.category;

import java.util.Locale;


public class CategoryQueryService {
    @Cacheable(cacheNames = "algashop:categories:v1", condition = "#filter.isCacheable(), key = "'default"))
    PageModel<CategoryDetailsOutput> filter(CategoryFilter filter);

    @Cacheable(cacheNames = "algashop:categories:v1", key = "#categoryId")
    CategoryDetailsOutput findById(UUID categoryId);
    
}
