package com.algaworks.algashop.domain.model.product;

public interface QuantityInStockAdjustment {

    Result increase(UUID productId, Integer quantity);
    Result decrease(UUID productId, Integer quantity);

    record Result(
        UUID productId,
        int previousQuantity,
        int newQuantity
    )

}
