package org.carrefour.leasing.core.service;


import org.carrefour.leasing.core.domain.model.Customer;
import reactor.core.publisher.Mono;

public class CustomerEligibilityService {

    public Mono<Void> validateEligibility(Customer customer) {
        if (customer.isBlacklisted()) {
            return Mono.error(new IllegalStateException("Customer is blacklisted"));
        }
        // Simulate businnes validation
        return Mono.just(customer)
                .flatMap(this::checkSolvency)
                .flatMap(isSolvent -> {
                    if (!isSolvent) {
                        return Mono.error(new IllegalStateException("Customer not solvent"));
                    }
                    return checkNoBadHistory(customer)
                            .flatMap(noBadHistory -> noBadHistory
                                    ? Mono.empty()
                                    : Mono.error(new IllegalStateException("Customer has bad rental history")));
                });
    }

    private Mono<Boolean> checkSolvency(Customer customer) {
        // TODO
        return Mono.just(true);
    }

    private Mono<Boolean> checkNoBadHistory(Customer customer) {
        // TODO
        return Mono.just(true);
    }
}
