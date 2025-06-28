package org.carrefour.leasing.application.dto;

import org.carrefour.leasing.common.model.CarType;

/**
 * Represents a car DTO.
 * This class contains the details of a car, including its identifier,
 * model ,type ...
 */
public class CarDTO {

    private Long id;

    private String brand;

    private String model;

    private CarType type;

    private String location;

    private double basePricePerDay;

    private boolean leased;

    public void lease() {
        if (leased) throw new IllegalStateException("Car already leased");
        leased = true;
    }

    public void returnCar() {
        if (!leased) throw new IllegalStateException("Car is not leased");
        leased = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getBasePricePerDay() {
        return basePricePerDay;
    }

    public void setBasePricePerDay(double basePricePerDay) {
        this.basePricePerDay = basePricePerDay;
    }

    public boolean isLeased() {
        return leased;
    }

    public void setLeased(boolean leased) {
        this.leased = leased;
    }
}

