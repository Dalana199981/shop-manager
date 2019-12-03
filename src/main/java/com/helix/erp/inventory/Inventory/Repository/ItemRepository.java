package com.helix.erp.inventory.Inventory.Repository;

import com.helix.erp.inventory.Inventory.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
}
