package com.helix.erp.inventory.Inventory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue
    Integer id;

    @OneToMany
    List<Stock> stocks;


}
