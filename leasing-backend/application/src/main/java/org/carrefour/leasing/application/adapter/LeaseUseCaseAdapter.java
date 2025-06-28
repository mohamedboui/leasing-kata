package org.carrefour.leasing.application.adapter;

import org.carrefour.leasing.application.dto.LeaseDTO;
import org.carrefour.leasing.application.dto.LeaseRequestDTO;
import org.carrefour.leasing.application.mapper.LeaseDTOMapper;
import org.carrefour.leasing.application.mapper.LeaseRequestDTOMapper;
import org.carrefour.leasing.core.domain.model.LeaseRequest;
import org.carrefour.leasing.core.usecase.LeaseUseCase;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LeaseUseCaseAdapter {

    private final LeaseUseCase leaseUseCase;
    private final LeaseDTOMapper leaseDTOMapper;
    private final LeaseRequestDTOMapper leaseRequestDTOMapper;

    public LeaseUseCaseAdapter(LeaseUseCase leaseUseCase, LeaseDTOMapper leaseDTOMapper, LeaseRequestDTOMapper leaseRequestDTOMapper) {
        this.leaseUseCase = leaseUseCase;
        this.leaseDTOMapper = leaseDTOMapper;
        this.leaseRequestDTOMapper = leaseRequestDTOMapper;
    }

    public Mono<LeaseDTO> createLeaseRequest(LeaseRequestDTO leaseRequestDTO) {
        LeaseRequest leaseRequest = leaseRequestDTOMapper.toDomain(leaseRequestDTO);
        return leaseUseCase.createLeaseRequest(leaseRequest).map(leaseDTOMapper::toDto);
    }

    public Mono<Void> returnLeasedCar(Long leaseId) {
        return leaseUseCase.returnLeasedCar(leaseId);
    }

    public Flux<LeaseDTO> getAllLeases() {
        return leaseUseCase.getAllLeases().map(leaseDTOMapper::toDto);
    }

    public Mono<LeaseDTO> getLeaseById(long leaseId) {
        return leaseUseCase.getLeaseById(leaseId).map(leaseDTOMapper::toDto);
    }
}
