package org.carrefour.leasing.dataprovider.model;

import org.carrefour.leasing.common.model.CarType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a car entity.
 * This class contains the details of a car, including its identifier,
 * model ,type ...
 */
@Table("car")
public class CarEntity {

    @Id
    private Long id;

    @Column("brand")
    private String brand;

    @Column("model")
    private String model;

    @Column("type")
    private CarType type;

    @Column("location")
    private String location;

    @Column("base_price_per_day")
    private double basePricePerDay;

    @Column("leased")
    private boolean leased;

    public CarEntity() {
    }

    public CarEntity(String brand, String model, CarType type, String location, double basePricePerDay, boolean leased) {
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.location = location;
        this.basePricePerDay = basePricePerDay;
        this.leased = leased;
    }

    public CarEntity(Long id, String brand, String model, CarType type, String location, double basePricePerDay, boolean leased) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.location = location;
        this.basePricePerDay = basePricePerDay;
        this.leased = leased;
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

