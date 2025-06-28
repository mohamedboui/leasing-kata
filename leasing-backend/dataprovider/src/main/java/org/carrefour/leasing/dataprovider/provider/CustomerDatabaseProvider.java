package org.carrefour.leasing.dataprovider.provider;

import org.carrefour.leasing.core.domain.model.Customer;
import org.carrefour.leasing.core.domain.provider.CustomerProvider;
import org.carrefour.leasing.dataprovider.mapper.CustomerMapper;
import org.carrefour.leasing.dataprovider.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomerDatabaseProvider implements CustomerProvider {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerDatabaseProvider(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public Mono<Customer> findCustomerById(long customerId) {
        return customerRepository.findById(customerId).map(customerMapper::toDomain);
    }
}
