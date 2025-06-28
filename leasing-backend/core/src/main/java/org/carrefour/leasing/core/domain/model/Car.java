package org.carrefour.leasing.core.domain.model;

import org.carrefour.leasing.common.model.CarType;

/**
 * Represents a car model.
 * This class contains the details of a car, including its identifier,
 * model ,type ...
 */
public class Car {

    private Long id;

    private String brand;

    private String model;

    private CarType type;

    private String location;

    private double basePricePerDay;

    private boolean leased;

    public Car(String brand, String model, CarType type, String location, double basePricePerDay, boolean leased) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.location = location;
        this.basePricePerDay = basePricePerDay;
        this.leased = leased;
    }

    public void lease() {
        leased = true;
    }

    public void returnCar() {
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

