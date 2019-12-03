package com.helix.erp.inventory.Inventory.Repository;

import com.helix.erp.inventory.Inventory.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrdersRepository extends JpaRepository<PurchaseOrder, Long> {
}
