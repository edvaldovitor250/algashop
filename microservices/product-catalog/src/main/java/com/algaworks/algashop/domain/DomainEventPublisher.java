package com.algaworks.algashop.domain;

public interface DomainEventPublisher {

    void publish(Object event);

}
