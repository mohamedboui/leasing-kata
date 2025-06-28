package org.carrefour.leasing.application.dto;


import java.time.LocalDateTime;

/**
 * Represents a leases DTO.
 * This class contains the details of a lease, including its identifier,
 * customer ,car ...
 */
public class LeaseDTO {

    private Long id;

    private CustomerDTO customer;

    private CarDTO car;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime actualReturnDate;

    public LeaseDTO() {
    }

    public LeaseDTO(CustomerDTO customerDTO, CarDTO carDTO, LocalDateTime startDate, LocalDateTime endDate) {
        this.customer = customerDTO;
        this.car = carDTO;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getActualReturnDate() {
        return actualReturnDate;
    }

    public void setActualReturnDate(LocalDateTime actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }
}
