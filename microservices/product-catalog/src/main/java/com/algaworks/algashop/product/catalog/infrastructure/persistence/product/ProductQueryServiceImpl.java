package com.algaworks.algashop.product.catalog.infrastructure.persistence.product;

import com.algaworks.algashop.product.catalog.application.PageModel;
import com.algaworks.algashop.product.catalog.application.ResourceNotFoundException;
import com.algaworks.algashop.product.catalog.application.product.query.ProductDetailOutput;
import com.algaworks.algashop.product.catalog.application.product.query.ProductQueryService;
import com.algaworks.algashop.product.catalog.application.utility.Mapper;
import com.algaworks.algashop.product.catalog.domain.model.product.Product;
import com.algaworks.algashop.product.catalog.domain.model.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final Mapper mapper;

    @Override
    public ProductDetailOutput findById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException());
        return mapper.convert(product, ProductDetailOutput.class);
    }

    @Override
    public PageModel<ProductDetailOutput> filter(ProductFilter filter) {
        int number = filter.getPage();
        int size = filter.getPageSize();
       PageModel<Product> products = productRepository.findAll(PageRequest.of(number,size));
       Page<ProductSummaryOutput> mapped = products.map(product -> mapper.convert(product, ProductSummaryOutput.class));
        return PageModel.of(mapper);
    }
}