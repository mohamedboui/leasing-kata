package org.carrefour.leasing.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.carrefour.leasing.application.adapter.LeaseUseCaseAdapter;
import org.carrefour.leasing.application.dto.LeaseDTO;
import org.carrefour.leasing.application.dto.LeaseRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * REST controller for managing car leases.
 */
@RestController
@RequestMapping("/api/leases")
public class LeasingController {

    private final LeaseUseCaseAdapter leaseUseCaseAdapter;

    public LeasingController(LeaseUseCaseAdapter leaseUseCaseAdapter) {
        this.leaseUseCaseAdapter = leaseUseCaseAdapter;
    }

    /**
     * Request a lease.
     *
     * @param request request a lease
     * @return a Mono of LeaseDTO
     */
    @Operation(summary = "Lease a car", description = "This method allows a customer to lease a car.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully leased",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeaseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Car or Customer not found"),
            @ApiResponse(responseCode = "400", description = "Car already leased or request invalid")
    })
    @PostMapping
    public Mono<ResponseEntity<LeaseDTO>> createLease(@RequestBody LeaseRequestDTO request) {
        return leaseUseCaseAdapter.createLeaseRequest(request).map(ResponseEntity::ok);
    }

    /**
     * Return a leased car.
     *
     * @param leaseId       the ID of the lease
     * @return a Mono<Void>
     */
    @Operation(summary = "Return a car", description = "This method returns a car previously leased.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car returned successfully"),
            @ApiResponse(responseCode = "404", description = "Lease not found"),
            @ApiResponse(responseCode = "400", description = "Lease already ended or invalid state")
    })
    @PutMapping("/{leaseId}/return")
    public Mono<ResponseEntity<Void>> returnCar(@PathVariable("leaseId") Long leaseId) {

        return leaseUseCaseAdapter.returnLeasedCar(leaseId)
                .then(Mono.just(ResponseEntity.ok().build()));
    }

    /**
     * Get a specific lease.
     *
     * @param leaseId lease ID
     * @return LeaseDTO
     */
    @Operation(summary = "Get lease by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lease found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = LeaseDTO.class))}),
            @ApiResponse(responseCode = "404", description = "Lease not found")
    })
    @GetMapping("/{leaseId}")
    public Mono<ResponseEntity<LeaseDTO>> getLease(@PathVariable("leaseId") Long leaseId) {
        return leaseUseCaseAdapter.getLeaseById(leaseId)
                .map(ResponseEntity::ok);
    }

    /**
     * List all leases.
     *
     * @return list of LeaseDTOs
     */
    @Operation(summary = "List all leases")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Leases retrieved successfully")
    })
    @GetMapping
    public Flux<LeaseDTO> listLeases() {
        return leaseUseCaseAdapter.getAllLeases();
    }
}
