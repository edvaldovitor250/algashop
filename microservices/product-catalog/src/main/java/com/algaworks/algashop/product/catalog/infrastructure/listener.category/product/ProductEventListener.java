package com.algaworks.algashop.product.catalog.infrastructure.listener.category.product;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.algaworks.algashop.product.catalog.domain.model.product.ProductPlacedOnSaleEvent;
import com.algaworks.algashop.product.catalog.domain.model.product.ProductPriceChangedEvent;
import com.algaworks.algashop.product.catalog.domain.model.product.ProductAddedEvent;
import com.algaworks.algashop.product.catalog.domain.model.product.ProductListedEvent;
import com.algaworks.algashop.product.catalog.domain.model.product.ProductDelistedEvent;

import lombok.extern.slf4j.Slf4j;

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

    @EventListener(ProductAddedEvent.class)
    private void handle(ProductAddedEvent event) {
        log.info("Handling ProductAddedEvent: {}", event);
    }

    @EventListener(ProductListedEvent.class)
    private void handle(ProductListedEvent event) {
        log.info("Handling ProductListedEvent: {}", event);
    }

    @EventListener(ProductDelistedEvent.class)
    private void handle(ProductDelistedEvent event) {
        log.info("Handling ProductDelistedEvent: {}", event);
    }

       @EventListener(ProductListedEvent.class)
    private void handle(ProductListedEvent event) {
        log.info("Handling ProductListedEvent: {}", event);
    }

    @EventListener(ProductDelistedEvent.class)
    private void handle(ProductDelistedEvent event) {
        log.info("Handling ProductDelistedEvent: {}", event);
    }


}
