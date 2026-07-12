package com.algaworks.algashop.product.catalog.infrastructure.security;

import com.algaworks.algashop.product.catalog.application.security.SecurityCheckApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service("securityCheck")
@Slf4j
public class OAuth2SecurityCheckApplicationServiceImpl
        implements SecurityCheckApplicationService {

    private static final String SCOPE_PRODUCTS_WRITE = "SCOPE_products:write";
    private static final String SCOPE_PRODUCTS_STOCK_WRITE = "SCOPE_products:stock:write";
    private static final String SCOPE_CATEGORIES_WRITE = "SCOPE_categories:write";
    private static final String ROLE_MANAGER = "ROLE_MANAGER";
    private static final String ROLE_OPERATOR = "ROLE_OPERATOR";

    @Override
    public UUID getAuthenticatedUserId() {
        if (isMachineAuthenticated()) {
            throw new AccessDeniedException("Machine users do not have user ID");
        }
        Jwt jwt = getJwt();

        try {
            return UUID.fromString(jwt.getSubject());
        } catch (IllegalArgumentException e) {
            log.error("Invalid user ID in JWT subject: {}", jwt.getSubject(), e);
            throw new AccessDeniedException("Invalid user ID in JWT subject");
        }
    }

    @Override
    public boolean isAuthenticated() {
        try {
            return getAuthentication().isAuthenticated();
        } catch (IllegalStateException e) {
            log.debug(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean isMachineAuthenticated() {
        Jwt jwt;
        try {
            jwt = getJwt();
        } catch (IllegalStateException e) {
            log.debug(e.getMessage(), e);
            return false;
        }
        return jwt.getAudience().contains(jwt.getSubject());
    }

    @Override
    public boolean isManagerOrOperator() {
        return hasAuthority(ROLE_MANAGER) || hasAuthority(ROLE_OPERATOR);
    }

    @Override
    public boolean canWriteProducts() {
        return isManagerOrOperator() && hasAuthority(SCOPE_PRODUCTS_WRITE);
    }

    @Override
    public boolean canWriteProductsStock() {
        return isMachineAuthenticated() && hasAuthority(SCOPE_PRODUCTS_STOCK_WRITE);
    }

    @Override
    public boolean canWriteCategories() {
        return isManagerOrOperator() && hasAuthority(SCOPE_CATEGORIES_WRITE);
    }

    private boolean hasAuthority(String rawAuthority) {
        Authentication authentication;
        try {
            authentication = getAuthentication();
        } catch (IllegalStateException e) {
            log.debug(e.getMessage(), e);
            return false;
        }
        return authentication.getAuthorities()
                .stream().anyMatch(a -> Objects.equals(a.getAuthority(), rawAuthority));
    }

    private Jwt getJwt() {
        Authentication authentication = getAuthentication();
        if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt;
        }
        throw new IllegalStateException("Authentication principal is not a JWT");
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new IllegalStateException("No authentication found");
        }
        return authentication;
    }

}
