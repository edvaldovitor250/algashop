package com.algaworks.algashop.product.catalog.application.security;

import java.util.UUID;

public interface SecurityCheckApplicationService {

    UUID getAuthenticatedUserId();
    boolean isAuthenticated();
    boolean isMachineAuthenticated();
    boolean isManagerOrOperator();
    boolean canWriteProducts();
    boolean canWriteProductsStock();
    boolean canWriteCategories();

}
