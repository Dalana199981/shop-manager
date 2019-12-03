package com.helix.erp.inventory.Inventory;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Location {
    @GeneratedValue
    @Id
    Long locationId;


    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    @Enumerated()
    LocationStatus status = LocationStatus.ACTIVE;

    public void inactivate() {
        this.status = LocationStatus.INACTIVE;
    }

    private enum LocationStatus {
        INACTIVE, ACTIVE
    }

    @ManyToOne()
    Location parent;
}
