package org.carrefour.leasing.core.domain.model;

import java.time.LocalDate;

public class LeaseRequest{
    private Long customerId;
    private Long carId;
    private LocalDate startDate;
    private LocalDate endDate;

    public LeaseRequest() {
    }

    public LeaseRequest(Long customerId, Long carId, LocalDate startDate, LocalDate endDate) {
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
