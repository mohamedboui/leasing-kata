package org.carrefour.leasing.dataprovider.mapper;

import org.carrefour.leasing.core.domain.model.Car;
import org.carrefour.leasing.dataprovider.model.CarEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarEntity toEntity(Car car);
    Car toDomain(CarEntity carEntity);
}
