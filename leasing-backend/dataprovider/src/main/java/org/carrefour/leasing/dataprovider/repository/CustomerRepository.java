package org.carrefour.leasing.dataprovider.repository;


import org.carrefour.leasing.dataprovider.model.CustomerEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends R2dbcRepository<CustomerEntity, Long> {
}
