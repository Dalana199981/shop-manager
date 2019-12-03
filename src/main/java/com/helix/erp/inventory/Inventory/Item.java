package com.helix.erp.inventory.Inventory;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    Category category;

    @Column
    String name;

    @Column
    String tradeName;

    @Basic(fetch = FetchType.LAZY)
    String description;

    @Column
    Float price;

    @Deprecated
    @Column
    Integer stock = 0;

    @Column
    String unit;

    @OneToMany(mappedBy = "item")
    List<Stock> stocks;

    @ManyToMany(cascade = {CascadeType.PERSIST}, targetEntity = Organization.class)
    @JoinTable()
    Set<Organization> suppliers;

    @Column
    Integer stockWarning;

    public int hashCode() {
        return id.intValue();
    }

}
