package com.algaworks.algashop.product.catalog.infrastructure.listener.category.product;

import org.springframework.context.event.EventListener;

import com.algaworks.algashop.product.catalog.domain.model.product.ProductPlacedOnSaleEvent;

@Component
@Slf4j
public class ProductEventListener {

    @EventListener(ProductPriceChangedEvent.class)
    private void handle(ProductPriceChangedEvent event) {
        log.info("Handling ProductPriceChangedEvent: {}", event);
    }

    @EventListener(ProductPlacedOnSaleEvent.class)
    private void handle(ProductPlacedOnSaleEvent event) {
        log.info("Handling ProductPlacedOnSaleEvent: {}", event);
    }

}
