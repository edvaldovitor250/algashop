package com.algaworks.algashop.domain.model.product;

@Getter
@Builder
public class ProductRestockedEvent {

    private UUID productId;
    
    @Builder.Default
    private OffsetDateTime restockedAt = OffsetDateTime.now();

}
