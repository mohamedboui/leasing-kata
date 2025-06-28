package org.carrefour.leasing.core.domain.provider;

import org.carrefour.leasing.core.domain.model.Customer;
import reactor.core.publisher.Mono;

public interface CustomerProvider {
    Mono<Customer> findCustomerById(long customerId);
}
