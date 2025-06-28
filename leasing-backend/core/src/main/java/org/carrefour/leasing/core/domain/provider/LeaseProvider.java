package org.carrefour.leasing.core.domain.provider;

import org.carrefour.leasing.core.domain.model.Lease;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LeaseProvider {
    Flux<Lease> findaActiveLeasesByCar(Long id);

    Flux<Lease> findActiveLeaseByCustomerAndCar(Long customerId, Long carId);

    Mono<Lease> saveLease(Lease lease);

    Mono<Lease> findLeaseById(Long leaseId);

    Flux<Lease> findAll();
}
