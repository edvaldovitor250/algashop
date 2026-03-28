package com.algaworks.algashop.product.catalog.infrastructure.message;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algashop.domain.DomainEventPublisher;
import com.algaworks.algashop.product.catalog.application.ApplicationMessagePublisher;

@Configuration
public class DomainEventPublisherConfig {

    Bean
    public DomainEventPublisher domainEventPublisher(
        ApplicationEventPublisher applicationEventPublisher
    ) {
        return applicationEventPublisher::publishEvent;
    }

}
