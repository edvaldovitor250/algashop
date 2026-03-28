package com.algaworks.algashop.domain.model.product;

public interface QuantityInStockAdjustment {

    Result increase(UUID productId, Integer quantity);
    Result decrease(UUID productId, Integer quantity);

    record Result(
        UUID productId,
        int previousQuantity,
        int newQuantity
    ){

        public boolean isOutOfStock() {
            return newQuantity == 0 && previousQuantity != 0;
        }

        public boolean isRestocked() {
            return newQuantity > 0 && previousQuantity == 0;
        }
    }

}
