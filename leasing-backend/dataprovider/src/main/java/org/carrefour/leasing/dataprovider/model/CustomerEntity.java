package org.carrefour.leasing.dataprovider.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Represents a customer entity.
 * This class contains the details of a customer, including its identifier,
 * name...
 */

@Table("customer")
public class CustomerEntity {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("blacklisted")
    private boolean blacklisted;

    public CustomerEntity() {
    }

    public CustomerEntity(Long id, String name, boolean blacklisted) {
        this.id = id;
        this.name = name;
        this.blacklisted = blacklisted;
    }

    public CustomerEntity(String name, boolean blacklisted) {
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