package org.carrefour.leasing.application.dto;

/**
 * Represents a customer DTO.
 * This class contains the details of a customer, including its identifier,
 * name...
 */
public class CustomerDTO {

    private Long id;

    private String name;

    private boolean blacklisted;

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