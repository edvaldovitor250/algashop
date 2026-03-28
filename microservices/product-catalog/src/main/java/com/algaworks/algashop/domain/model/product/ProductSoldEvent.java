package com.algaworks.algashop.domain.model.product;

@Getter
@Builder
public class ProductSoldEvent {
    private UUID productId;
    
    @Builder.Default
    private OffsetDateTime soldOutAt = OffsetDateTime.now();

}
