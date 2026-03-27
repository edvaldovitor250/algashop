package com.algaworks.algashop.product.catalog.application.category.event;

import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryUpdatedEvent {
    private UUID categoryId;
    private String name;
    private Boolean enabled;
    
}
