package org.carrefour.leasing.core.service;

import org.carrefour.leasing.common.model.CarType;
import org.carrefour.leasing.core.domain.model.*;
import org.carrefour.leasing.core.domain.provider.CarProvider;
import org.carrefour.leasing.core.domain.provider.CustomerProvider;
import org.carrefour.leasing.core.domain.provider.LeaseProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class LeaseUseCaseImplTest {

    @Mock
    private CarProvider carProvider;

    @Mock
    private CustomerProvider customerProvider;

    @Mock
    private LeaseProvider leaseProvider;

    @Mock
    private CustomerEligibilityService eligibilityService;

    @InjectMocks
    private LeaseUseCaseImpl leaseUseCase;

    private Customer customer;
    private Car car;

    @BeforeEach
    void setup() {
        customer = new Customer("Alice", false);
        customer.setId(1L);

        car = new Car("Renault", "Clio", CarType.ECONOMY, "Paris", 50.0, false);
        car.setId(2L);
    }

    @Test
    void shouldCreateLeaseSuccessfully() {
        LeaseRequest request = new LeaseRequest(
                customer.getId(),
                car.getId(),
                LocalDate.now(),
                LocalDate.now().plusDays(3)
        );

        when(customerProvider.findCustomerById(1L)).thenReturn(Mono.just(customer));
        when(carProvider.findCarById(2L)).thenReturn(Mono.just(car));
        when(eligibilityService.validateEligibility(customer)).thenReturn(Mono.empty());
        when(leaseProvider.findaActiveLeasesByCar(2L)).thenReturn(Flux.empty());
        when(leaseProvider.saveLease(any())).thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));
        when(carProvider.saveCar(any())).thenReturn(Mono.just(car));

        StepVerifier.create(leaseUseCase.createLeaseRequest(request))
                .assertNext(lease -> {
                    assert lease.getCustomer().getId().equals(1L);
                    assert lease.getCar().getId().equals(2L);
                    assert lease.getStartDate().toLocalDate().equals(request.getStartDate());
                    assert lease.getEndDate().toLocalDate().equals(request.getEndDate());
                })
                .verifyComplete();
    }

    @Test
    void shouldFailIfMissingFields() {
        LeaseRequest request = new LeaseRequest(null, null, null, null);

        StepVerifier.create(leaseUseCase.createLeaseRequest(request))
                .expectErrorMatches(e -> e instanceof IllegalArgumentException &&
                        e.getMessage().contains("mandatory"))
                .verify();
    }

    @Test
    void shouldFailIfLeaseConflict() {
        LeaseRequest request = new LeaseRequest(
                customer.getId(),
                car.getId(),
                LocalDate.now(),
                LocalDate.now().plusDays(2)
        );

        Lease existingLease = new Lease(customer, car,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().plusDays(1)
        );

        when(customerProvider.findCustomerById(1L)).thenReturn(Mono.just(customer));
        when(carProvider.findCarById(2L)).thenReturn(Mono.just(car));
        when(eligibilityService.validateEligibility(customer)).thenReturn(Mono.empty());
        when(leaseProvider.findaActiveLeasesByCar(2L)).thenReturn(Flux.just(existingLease));

        StepVerifier.create(leaseUseCase.createLeaseRequest(request))
                .expectErrorMatches(e -> e instanceof IllegalStateException &&
                        e.getMessage().equals("Car already leased in this period"))
                .verify();
    }

    @Test
    void shouldReturnCarSuccessfully() {
        car.setLeased(true);
        Lease lease = new Lease( customer, car, LocalDateTime.now().minusDays(3), null);
        lease.setId(1L);
        when(leaseProvider.findLeaseById(1L)).thenReturn(Mono.just(lease));
        when(leaseProvider.saveLease(any())).thenReturn(Mono.just(lease));
        when(carProvider.saveCar(any())).thenReturn(Mono.just(car));

        StepVerifier.create(leaseUseCase.returnLeasedCar(1L))
                .verifyComplete();
    }

    @Test
    void shouldFailReturnIfLeaseNotFound() {
        // Given
        when(leaseProvider.findLeaseById(999L))
                .thenReturn(Mono.empty()); // simule un lease non trouvé

        // When
        Mono<Void> result = leaseUseCase.returnLeasedCar(999L);

        // Then
        StepVerifier.create(result)
                .expectErrorMatches(throwable ->
                        throwable instanceof IllegalStateException &&
                                throwable.getMessage().equals("Lease not found"))
                .verify();
    }
}
