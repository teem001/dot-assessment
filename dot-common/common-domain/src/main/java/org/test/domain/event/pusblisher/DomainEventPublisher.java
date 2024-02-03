package org.test.domain.event.pusblisher;

import org.test.domain.event.DomainEvent;

public interface DomainEventPublisher <T extends DomainEvent>{

    void publish(T domainEvent);
}
