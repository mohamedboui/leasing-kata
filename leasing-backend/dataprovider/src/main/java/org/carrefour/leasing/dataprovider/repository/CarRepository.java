package org.carrefour.leasing.dataprovider.repository;


import org.carrefour.leasing.dataprovider.model.CarEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CarRepository extends R2dbcRepository<CarEntity, Long>, CarCustomRepository {
    Flux<CarEntity> findAll();
}