package org.carrefour.leasing.dataprovider.mapper;

import org.carrefour.leasing.core.domain.model.Customer;
import org.carrefour.leasing.dataprovider.model.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toEntity(Customer customer);

    Customer toDomain(CustomerEntity customerEntity);
}
