package org.carrefour.leasing.dataprovider.mapper;

import org.carrefour.leasing.core.domain.model.Lease;
import org.carrefour.leasing.dataprovider.model.LeaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface LeaseMapper {
    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "carId", target = "car.id")
    Lease toDomain(LeaseEntity entity);

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "car.id", target = "carId")
    LeaseEntity toEntity(Lease lease);
}
