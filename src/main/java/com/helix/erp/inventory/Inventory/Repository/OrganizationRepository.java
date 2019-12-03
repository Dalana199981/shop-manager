package com.helix.erp.inventory.Inventory.Repository;

import com.helix.erp.inventory.Inventory.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {
}
