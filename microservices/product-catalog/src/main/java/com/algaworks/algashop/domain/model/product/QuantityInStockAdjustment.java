package com.algaworks.algashop.domain.model.product;

public interface QuantityInStockAdjustment {

    void increase(UUID productId, Integer quantity);
    void decrease(UUID productId, Integer quantity);

}
