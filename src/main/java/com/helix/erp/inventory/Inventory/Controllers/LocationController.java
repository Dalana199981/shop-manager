package com.helix.erp.inventory.Inventory.Controllers;

import com.helix.erp.inventory.Inventory.Location;
import com.helix.erp.inventory.Inventory.Repository.LocationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("locations")
@RestController(value = "locations")
@Tag(name = "Locations", description = "Internal Units and Locations")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @GetMapping
    Page<Location> getLocations(PageRequest pageRequest) {
        return locationRepository.findAll(pageRequest);
    }

    @PostMapping
    Location createLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @GetMapping(path = {"{locationId}"})
    Location getSupplier(@PathVariable Location location) {
        return location;
    }

    @PatchMapping(path = {"{locationId}"})
    Location updateLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    @DeleteMapping(path = {"{locationId}"})
    Location deactivateOrg(@PathVariable Location location) {
        location.inactivate();
        return locationRepository.save(location);
    }
}
