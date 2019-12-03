package com.helix.erp.inventory.Inventory.Repository;

import com.helix.erp.inventory.Inventory.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
