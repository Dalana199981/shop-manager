package com.helix.erp.inventory.Inventory.Controllers;

import com.helix.erp.inventory.Inventory.GRN;
import com.helix.erp.inventory.Inventory.PurchaseOrder;
import com.helix.erp.inventory.Inventory.Repository.PurchaseOrdersRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("purchase-order")
@RestController
@CrossOrigin("*")
@Tag(name = "Purchase Orders", description = "Purchase Order Related")
public class PurchaseOrderController {

    @Autowired
    PurchaseOrdersRepository purchaseOrdersRepository;

    @Operation(summary = "Create A New Purchase Order", description = "Name search by %name% format")
    @PostMapping
    PurchaseOrder savePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        purchaseOrdersRepository.save(purchaseOrder);
        return purchaseOrder;
    }

    @GetMapping
    public Page<PurchaseOrder> getPurchaseOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return purchaseOrdersRepository.findAll(PageRequest.of(page, size));
    }

    @GetMapping("{purchaseOrderId}")
    PurchaseOrder getPurchaseOrder(@PathVariable() Long purchaseOrderId) {
        return purchaseOrdersRepository.getOne(purchaseOrderId);
    }

    @PutMapping("{purchaseOrderId}")
    PurchaseOrder updatePurchaseOrder(@PathVariable() Long purchaseOrderId) {
        return purchaseOrdersRepository.getOne(purchaseOrderId);
    }

    @Operation(summary = "test", description = "ddd")
    @PostMapping("{purchaseOrderId}/grn")
    GRN createGRN(@RequestBody GRN grn) {
        return grn;
    }


}
