package org.carrefour.leasing.application.controller;

import org.carrefour.leasing.application.dto.LeaseDTO;
import org.carrefour.leasing.application.dto.LeaseRequestDTO;
import org.carrefour.leasing.dataprovider.repository.LeaseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@Testcontainers
public class LeasingControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private LeaseRepository leaseRepository;
    @Container
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        String r2dbcUrl = String.format(
                "r2dbc:mysql://%s:%d/%s",
                mysql.getHost(),
                mysql.getFirstMappedPort(),
                mysql.getDatabaseName()
        );
        registry.add("spring.r2dbc.url", () -> r2dbcUrl);
        registry.add("spring.r2dbc.username", mysql::getUsername);
        registry.add("spring.r2dbc.password", mysql::getPassword);
    }
    private LeaseRequestDTO leaseRequest;


    @BeforeEach
    void setup() {
        // on bloque pour que le nettoyage soit terminé avant chaque test
        leaseRepository.deleteAll().block();
        leaseRequest = new LeaseRequestDTO();
        leaseRequest.setCarId(1L);
        leaseRequest.setCustomerId(1L);
        leaseRequest.setStartDate(LocalDate.now());
        leaseRequest.setEndDate(LocalDate.now().plusDays(7));
        // Ajoute d'autres champs de retour si besoin
    }


    @Test
    void testCreateLease_missingCustomerId_shouldReturn400() {
        leaseRequest.setCustomerId(null);
        webTestClient.post()
                .uri("/api/leases")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(leaseRequest), LeaseRequestDTO.class)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testCreateAndReturnCar_success() {
        // 1) créer un lease
        LeaseRequestDTO createLease = new LeaseRequestDTO();
        createLease.setCarId(2L);
        createLease.setCustomerId(1L);
        createLease.setStartDate(LocalDate.now());
        createLease.setEndDate(LocalDate.now().plusDays(3));

        LeaseDTO newLease = webTestClient.post()
                .uri("/api/leases")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(createLease), LeaseRequestDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LeaseDTO.class)
                .returnResult()
                .getResponseBody();
        assertEquals(2L, newLease.getCar().getId());
        assertEquals(1L, newLease.getCustomer().getId());
        assertNotNull(newLease.getStartDate());
        assertNotNull(newLease.getEndDate());
        assertNull(newLease.getActualReturnDate());

        // 2) retourner la voiture en ne passant que customerId + carId

        webTestClient.put()
                .uri("/api/leases/{leaseId}/return", newLease.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testReturnCar_invalidLease_shouldReturn404() {

        webTestClient.put()
                .uri("/api/leases/{leaseId}/return", 9999L)
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testGetLeaseById_success() {
        // Crée un lease pour récupérer
        LeaseRequestDTO createLease = new LeaseRequestDTO();
        createLease.setCarId(1L);
        createLease.setCustomerId(1L);
        createLease.setStartDate(LocalDate.now());
        createLease.setEndDate(LocalDate.now().plusDays(2));

        var leaseId = webTestClient.post()
                .uri("/api/leases")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(createLease), LeaseRequestDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(LeaseDTO.class)
                .returnResult()
                .getResponseBody()
                .getId();

        webTestClient.get()
                .uri("/api/leases/{leaseId}", leaseId)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(leaseId.intValue());
    }

    @Test
    void testListLeases() {
        webTestClient.get()
                .uri("/api/leases")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(LeaseDTO.class);
    }
}
