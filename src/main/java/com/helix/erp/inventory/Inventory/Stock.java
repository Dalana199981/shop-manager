package com.helix.erp.inventory.Inventory;

import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Stock {

    @Id
    @GeneratedValue
    @Getter
    UUID uuid;

    @ManyToOne
    Item item;

    @Column
    Integer initialStock;

    @Column
    Integer currentStock;

    @Column
    @Enumerated
    StockStatus status;


    @ManyToOne
    GRN grn;

}
