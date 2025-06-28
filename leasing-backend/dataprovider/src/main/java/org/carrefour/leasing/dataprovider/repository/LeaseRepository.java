package org.carrefour.leasing.dataprovider.repository;


import org.carrefour.leasing.dataprovider.model.LeaseEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LeaseRepository extends R2dbcRepository<LeaseEntity, Long> {

    Flux<LeaseEntity> findByCustomerIdAndCarIdAndActualReturnDateIsNull(Long customerId, Long carId);

    Flux<LeaseEntity> findByCarIdAndActualReturnDateIsNull(Long carId);
}
