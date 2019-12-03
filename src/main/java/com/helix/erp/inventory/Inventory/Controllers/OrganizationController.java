package com.helix.erp.inventory.Inventory.Controllers;

import com.helix.erp.inventory.Inventory.Organization;
import com.helix.erp.inventory.Inventory.Repository.OrganizationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("organizations")
@RestController(value = "organizations")
@Tag(name = "Organizations", description = "Outside Organizations such as Suppliers, Contractors")
public class OrganizationController {

    @Autowired
    OrganizationRepository organizationRepository;

    @GetMapping
    Page<Organization> getOrganizations(PageRequest pageRequest) {
        return organizationRepository.findAll(pageRequest);
    }

    @PostMapping
    Organization createOrganization(@RequestBody Organization organization) {
        return organizationRepository.save(organization);
    }

    @GetMapping(path = {"{organizationId}"})
    Organization getSupplier(@PathVariable Organization organization) {
        return organization;
    }

    @PatchMapping(path = {"{organizationId}"})
    Organization updateOrganization(@RequestBody Organization organization) {
        return organizationRepository.save(organization);
    }

    @DeleteMapping(path = {"{organizationId}"})
    Organization deactivateOrg(@PathVariable Organization organization) {
        organization.inactivate();
        return organizationRepository.save(organization);
    }
}
