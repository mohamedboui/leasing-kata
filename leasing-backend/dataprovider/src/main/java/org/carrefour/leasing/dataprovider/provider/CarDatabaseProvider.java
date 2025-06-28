package org.carrefour.leasing.dataprovider.provider;


import org.carrefour.leasing.common.model.CarSearchCriteria;
import org.carrefour.leasing.core.domain.model.Car;
import org.carrefour.leasing.core.domain.provider.CarProvider;
import org.carrefour.leasing.dataprovider.mapper.CarMapper;
import org.carrefour.leasing.dataprovider.mapper.CustomerMapper;
import org.carrefour.leasing.dataprovider.model.CarEntity;
import org.carrefour.leasing.dataprovider.repository.CarRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CarDatabaseProvider implements CarProvider {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarDatabaseProvider(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    @Override
    public Mono<Car> findCarById(Long id) {
        return carRepository.findById(id).map(carMapper::toDomain);
    }

    @Override
    @Transactional
    public Mono<Car> saveCar(Car car) {
        CarEntity entity = carMapper.toEntity(car);
        return carRepository.save(entity).map(carMapper::toDomain);
    }

    @Override
    public Flux<Car> findAvailableCars() {
        return carRepository.findAll()
                .filter(carEntity -> !carEntity.isLeased())
                .map(carMapper::toDomain);
    }

    @Override
    public Flux<Car> findAvailableCarsByCriteria(CarSearchCriteria criteria) {
        return carRepository.findAvailableCarsByCriteria(criteria).map(carMapper::toDomain);
    }
}
