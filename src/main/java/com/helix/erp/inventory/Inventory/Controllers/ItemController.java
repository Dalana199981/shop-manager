package com.helix.erp.inventory.Inventory.Controllers;

import com.helix.erp.inventory.Inventory.Category;
import com.helix.erp.inventory.Inventory.Item;
import com.helix.erp.inventory.Inventory.Repository.CategoryRepository;
import com.helix.erp.inventory.Inventory.Repository.ItemRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("item")
@Tag(name = "Catalog & Items", description = "Manage Item Catalog")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Operation(summary = "Get / Filter All Items")
    @GetMapping
    Page<Item> getItems(@RequestBody Item item) {
        return itemRepository.findAll(Example.of(item), PageRequest.of(0, 10));
    }

    @GetMapping("category")
    List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping(path = {"{itemCode}"})
    Optional<Item> getItem(Model model, @PathVariable Long itemCode) {
        return itemRepository.findById(itemCode);
    }

    @PostMapping()
    @Transactional
    Item saveItem(@RequestBody() Item item) {
        return itemRepository.saveAndFlush(item);
    }

}
