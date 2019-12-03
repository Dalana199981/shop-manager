package com.helix.erp.inventory.Inventory;

import lombok.Data;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
public class GRN {

    @Id
    @GeneratedValue
    String grnNo;

    @ManyToOne
    PurchaseOrder purchaseOrder;

    @OneToMany(mappedBy = "grn")
    List<Stock> items;

}
