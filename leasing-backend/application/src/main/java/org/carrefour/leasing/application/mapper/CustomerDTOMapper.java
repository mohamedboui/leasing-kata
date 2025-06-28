package org.carrefour.leasing.application.mapper;

import org.carrefour.leasing.application.dto.CustomerDTO;
import org.carrefour.leasing.core.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDTOMapper {
    Customer toDomain(CustomerDTO customerDTO);

    CustomerDTO toDto(Customer customer);
}
