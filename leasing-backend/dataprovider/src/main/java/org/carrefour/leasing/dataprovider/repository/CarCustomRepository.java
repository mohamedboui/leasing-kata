package org.carrefour.leasing.dataprovider.repository;

import org.carrefour.leasing.common.model.CarSearchCriteria;
import org.carrefour.leasing.dataprovider.model.CarEntity;
import reactor.core.publisher.Flux;

public interface CarCustomRepository {
    Flux<CarEntity> findAvailableCarsByCriteria(CarSearchCriteria criteria);
}