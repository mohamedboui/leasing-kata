package org.carrefour.leasing.application.mapper;

import org.carrefour.leasing.application.dto.LeaseRequestDTO;
import org.carrefour.leasing.core.domain.model.LeaseRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaseRequestDTOMapper {
    LeaseRequest toDomain(LeaseRequestDTO leaseRequestDTO);

    LeaseRequestDTO toDto(LeaseRequest leaseRequest);
}
