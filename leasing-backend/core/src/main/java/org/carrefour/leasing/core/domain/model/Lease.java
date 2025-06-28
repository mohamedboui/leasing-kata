package org.carrefour.leasing.core.domain.model;


import java.time.LocalDateTime;

/**
 * Represents a leases model.
 * This class contains the details of a lease, including its identifier,
 * customer ,car ...
 */
public class Lease {

    private Long id;

    private Customer customer;

    private Car car;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime actualReturnDate;
    public Lease(Customer customer, Car car, LocalDateTime startDate, LocalDateTime endDate) {
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void endLease(LocalDateTime actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
}
