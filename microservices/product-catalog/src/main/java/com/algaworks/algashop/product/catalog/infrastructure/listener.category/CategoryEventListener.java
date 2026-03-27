package com.algaworks.algashop.product.catalog.infrastructure.listener.category;

@Component
@Slf4j
public class CategoryEventListener {

    @EventListener
    public void listen(CategoryCreatedEvent categoryUpdatedEvent) {
        log.info("Category updated received: {}", categoryUpdatedEvent.getCategoryId());
    }
}
