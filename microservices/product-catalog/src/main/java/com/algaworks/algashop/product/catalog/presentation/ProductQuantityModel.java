package com.algaworks.algashop.product.catalog.presentation;

@Data
public class ProductQuantityModel {
    
    @NotNull
    @Min(1)
    private Integer quantity;
}
