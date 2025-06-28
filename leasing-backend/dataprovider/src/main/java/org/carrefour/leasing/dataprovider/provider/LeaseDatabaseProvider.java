package org.carrefour.leasing.dataprovider.provider;

import org.carrefour.leasing.core.domain.model.Lease;
import org.carrefour.leasing.core.domain.provider.LeaseProvider;
import org.carrefour.leasing.dataprovider.mapper.LeaseMapper;
import org.carrefour.leasing.dataprovider.model.LeaseEntity;
import org.carrefour.leasing.dataprovider.repository.LeaseRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class LeaseDatabaseProvider implements LeaseProvider {

    private final LeaseRepository leaseRepository;
    private final LeaseMapper leaseMapper;

    public LeaseDatabaseProvider(LeaseRepository leaseRepository, LeaseMapper leaseMapper) {
        this.leaseRepository = leaseRepository;
        this.leaseMapper = leaseMapper;
    }

    @Override
    public Flux<Lease> findaActiveLeasesByCar(Long id) {
        return leaseRepository.findByCarIdAndActualReturnDateIsNull(id).map(leaseMapper::toDomain);
    }

    @Override
    public Flux<Lease> findActiveLeaseByCustomerAndCar(Long customerId, Long carId) {
        return leaseRepository.findByCustomerIdAndCarIdAndActualReturnDateIsNull(customerId, carId).map(leaseMapper::toDomain);
    }

    @Override
    @Transactional
    public Mono<Lease> saveLease(Lease lease) {
        LeaseEntity leaseEntity = leaseMapper.toEntity(lease);
        return leaseRepository.save(leaseEntity).map(leaseMapper::toDomain);
    }

    @Override
    public Mono<Lease> findLeaseById(Long leaseId) {
        return leaseRepository.findById(leaseId).map(leaseMapper::toDomain);
    }

    @Override
    public Flux<Lease> findAll() {
        return leaseRepository.findAll().map(leaseMapper::toDomain);
    }
}
