package com.algaworks.algashop.product.catalog.infrastructure.message;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;

import com.algaworks.algashop.product.catalog.application.ApplicationMessagePublisher;

@Configurable
public class ApplicationMessagePublisherConfig {

    @Bean
    public ApplicationMessagePublisher applicationMessagePublisher(
        ApplicationEventPublisher applicationEventPublisher
    ) {
        return applicationEventPublisher::publishEvent;
    }
    
}
