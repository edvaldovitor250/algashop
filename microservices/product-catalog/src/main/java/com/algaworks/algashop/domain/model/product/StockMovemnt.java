package com.algaworks.algashop.domain.model.product;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Document(Collection = "stock_movements")
@Getter
@NOArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class StockMovemnt {

    @Id
    @EqualsAndHashCode
    private UUID id;
    private OffsetDateTime occurredAt;
    private UUID productId;
    private Integer movementQuantity;
    private Integer previousQuantity;
    private Integer newQuantity;
    private MovementType type;

    @Builder
    public StockMovemnt(UUID productId, Integer movementQuantity, Integer previousQuantity, MovementType type) {
        this.id = UUID.randomUUID();
        this.occurredAt = OffsetDateTime.now();
        this.productId = productId;
        this.movementQuantity = movementQuantity;
        this.previousQuantity = previousQuantity;
        this.newQuantity = previousQuantity + (type == MovementType.STOCK_IN ? movementQuantity : -movementQuantity);
        this.type = type;
    }

    public enum MovementType {
        STOCK_IN,
        STOCK_OUT
    }
    
}
