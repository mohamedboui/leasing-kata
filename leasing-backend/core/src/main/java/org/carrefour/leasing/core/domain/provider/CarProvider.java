package org.carrefour.leasing.core.domain.provider;

import org.carrefour.leasing.common.model.CarSearchCriteria;
import org.carrefour.leasing.core.domain.model.Car;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarProvider {
    Mono<Car> findCarById(Long id);

    Mono<Car> saveCar(Car car);

    Flux<Car> findAvailableCars();

    Flux<Car> findAvailableCarsByCriteria(CarSearchCriteria criteria);

}
