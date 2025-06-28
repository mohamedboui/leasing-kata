package org.carrefour.leasing.application.mapper;

import org.carrefour.leasing.application.dto.CarDTO;
import org.carrefour.leasing.application.dto.LeaseDTO;
import org.carrefour.leasing.core.domain.model.Car;
import org.carrefour.leasing.core.domain.model.Lease;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarDTOMapper {
    Car toDomain(CarDTO carDTO);

    CarDTO toDto(Car car);
}
