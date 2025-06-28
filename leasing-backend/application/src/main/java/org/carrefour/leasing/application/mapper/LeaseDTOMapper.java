package org.carrefour.leasing.application.mapper;

import org.carrefour.leasing.application.dto.LeaseDTO;
import org.carrefour.leasing.core.domain.model.Lease;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LeaseDTOMapper {
    Lease toDomain(LeaseDTO leaseDTO);
    LeaseDTO toDto(Lease lease);
}
