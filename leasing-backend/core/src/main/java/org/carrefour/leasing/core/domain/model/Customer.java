package org.carrefour.leasing.core.domain.model;

/**
 * Represents a customer model.
 * This class contains the details of a customer, including its identifier,
 * name...
 */
public class Customer {

    private Long id;

    private String name;

    private boolean blacklisted;

    public Customer(String name, boolean blacklisted) {
        this.name = name;
        this.blacklisted = blacklisted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBlacklisted() {
        return blacklisted;
    }

    public void setBlacklisted(boolean blacklisted) {
        this.blacklisted = blacklisted;
    }
}