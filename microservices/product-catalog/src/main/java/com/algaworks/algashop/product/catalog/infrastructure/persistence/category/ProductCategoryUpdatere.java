package com.algaworks.algashop.product.catalog.infrastructure.persistence.category;

@Component
@AllArgsConstructor
public class ProductCategoryUpdatere {

    private final MongoOperations mongoOperations;

    public void copyCategoryDataToProducts(CategoryUpdatedEvent categoryUpdatedEvent) {
        Query query = new Query(Criteria.where("category.id").is(categoryUpdatedEvent.getCategoryId()));
        Update update = new Update()
                .set("category.name", categoryUpdatedEvent.getName())
                .set("category.enabled", categoryUpdatedEvent.getEnabled());
        mongoOperations.updateMulti(query, update, ProductDocument.class);

    }
}
