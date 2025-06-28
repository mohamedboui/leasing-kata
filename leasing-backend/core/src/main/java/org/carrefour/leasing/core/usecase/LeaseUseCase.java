package org.carrefour.leasing.core.usecase;

import org.carrefour.leasing.core.domain.model.Lease;
import org.carrefour.leasing.core.domain.model.LeaseRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LeaseUseCase {
    Mono<Lease> createLeaseRequest(LeaseRequest request);

    Mono<Void> returnLeasedCar(Long leaseId);

    Mono<Lease> getLeaseById(Long leaseId);

    Flux<Lease> getAllLeases();
}