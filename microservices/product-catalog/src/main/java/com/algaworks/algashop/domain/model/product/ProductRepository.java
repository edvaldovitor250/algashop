package com.algaworks.algashop.domain.model.product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface  ProductRepository extends MongoRepository<Product, UUID> {

}
