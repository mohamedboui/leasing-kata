package org.carrefour.leasing.core.service;

import org.carrefour.leasing.common.model.CarSearchCriteria;
import org.carrefour.leasing.core.domain.model.*;
import org.carrefour.leasing.core.domain.provider.CarProvider;
import org.carrefour.leasing.core.domain.provider.CustomerProvider;
import org.carrefour.leasing.core.domain.provider.LeaseProvider;
import org.carrefour.leasing.core.usecase.LeaseUseCase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class LeaseUseCaseImpl implements LeaseUseCase {

    private final CarProvider carProvider;
    private final CustomerProvider customerRepository;
    private final LeaseProvider leaseProvider;
    private final CustomerEligibilityService eligibilityService;

    public LeaseUseCaseImpl(CarProvider carProvider, CustomerProvider customerRepository, LeaseProvider leaseProvider, CustomerEligibilityService eligibilityService) {
        this.carProvider = carProvider;
        this.customerRepository = customerRepository;
        this.leaseProvider = leaseProvider;
        this.eligibilityService = eligibilityService;
    }


    public Flux<Car> findAvailableCarsByCriteria(CarSearchCriteria criteria) {
        return carProvider.findAvailableCarsByCriteria(criteria);
    }

    @Override
    public Mono<Lease> createLeaseRequest(LeaseRequest request) {
        if (request.getCustomerId() == null || request.getCarId() == null
                || request.getStartDate() == null || request.getEndDate() == null) {
            return Mono.error(new IllegalArgumentException("Customer, Car and dates are mandatory"));
        }

        Mono<Customer> customerMono = customerRepository.findCustomerById(request.getCustomerId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Customer not found")));

        Mono<Car> carMono = carProvider.findCarById(request.getCarId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Car not found")));

        return Mono.zip(customerMono, carMono)
                .flatMap(tuple -> {
                    Customer customer = tuple.getT1();
                    Car car = tuple.getT2();

                    return eligibilityService.validateEligibility(customer)
                            .then(leaseProvider.findaActiveLeasesByCar(car.getId())
                                    .filter(lease -> {
                                        LocalDateTime startRequested = request.getStartDate().atStartOfDay();
                                        LocalDateTime endRequested = request.getEndDate().atStartOfDay();
                                        LocalDateTime existingStart = lease.getStartDate();
                                        LocalDateTime existingEnd = lease.getEndDate() == null ? LocalDateTime.MAX : lease.getEndDate();

                                        return (startRequested.isBefore(existingEnd) && endRequested.isAfter(existingStart));
                                    })
                                    .hasElements()
                                    .flatMap(conflict -> {
                                        if (Boolean.TRUE.equals(conflict)) {
                                            return Mono.error(new IllegalStateException("Car already leased in this period"));
                                        }
                                        return Mono.empty();
                                    }))
                            .then(Mono.defer(() -> {
                                Lease lease = new Lease(customer, car,
                                        request.getStartDate().atStartOfDay(), request.getEndDate().atStartOfDay());
                                car.lease();
                                return leaseProvider.saveLease(lease)
                                        .flatMap(savedLease -> carProvider.saveCar(car).thenReturn(savedLease));
                            }));
                });
    }

    @Override
    public Mono<Void> returnLeasedCar(Long leaseId) {
        if (leaseId == null) {
            return Mono.error(new IllegalArgumentException("LeaseId is required"));
        }

        return leaseProvider.findLeaseById(leaseId)
                .switchIfEmpty(Mono.error(new IllegalStateException("Lease not found")))
                .flatMap(lease -> {
                    if (lease.getActualReturnDate() != null) {
                        return Mono.error(new IllegalStateException("Lease already returned"));
                    }

                    lease.endLease(LocalDateTime.now());
                    lease.getCar().returnCar();

                    return leaseProvider.saveLease(lease)
                            .then(carProvider.saveCar(lease.getCar()))
                            .then();
                });
    }

    @Override
    public Mono<Lease> getLeaseById(Long leaseId) {
        return leaseProvider.findLeaseById(leaseId);
    }

    @Override
    public Flux<Lease> getAllLeases() {
        return leaseProvider.findAll();
    }
}
