package com.algaworks.algashop.product.catalog.infrastructure.listener.category;

import org.springframework.scheduling.annotation.Async;

import com.algaworks.algashop.product.catalog.infrastructure.persistence.category.ProductCategoryUpdatere;

import lombok.AllArgsConstructor;

@Component
@Slf4j
@AllArgsConstructor
public class CategoryEventListener {

    private final ProductCategoryUpdatere productCategoryUpdatere;

    @EventListener
    @Async
    public void handle(CategoryCreatedEvent categoryUpdatedEvent) {
        productCategoryUpdatere.copyCategoryDataToProducts(categoryUpdatedEvent);
    }
}
