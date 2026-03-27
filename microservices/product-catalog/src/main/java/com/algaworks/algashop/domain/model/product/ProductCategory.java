package com.algaworks.algashop.domain.model.product;

@Document(collection = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory {

    private UUID id;
    private String name;
    private Boolean enabled;

    public static ProductCategory of(Category category) {
       return new ProductCategory(category.getId(), category.getName(), category.getEnabled());
    }
    
}
