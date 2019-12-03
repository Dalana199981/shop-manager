package com.helix.erp.inventory.Inventory;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Organization {

    @Id
    @GeneratedValue
    UUID supplierId;

    @Column
    @Embedded
    ContactInfo contactInfo;

    @Column
    String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "suppliers", cascade = CascadeType.PERSIST)
    Set<Item> items;

    OrganizationStatus status = OrganizationStatus.ACTIVE;

    public String toString() {
        return "Supplier[" + this.supplierId + " - " + this.name + "]";
    }

    public int hashCode() {
        return supplierId.hashCode();
    }

    private enum OrganizationStatus {
        INACTIVATE, ACTIVE, BLACKLISTED
    }

    public void inactivate() {
        this.status = OrganizationStatus.INACTIVATE;
    }

}
