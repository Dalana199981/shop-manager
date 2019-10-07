package com.dalana.ShopManager.Inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping
    String getItems(Model model, Pageable pageable) {
        model.addAttribute("items", itemRepository.findAll(pageable));
        return "items/items";
    }

    @GetMapping(path = {"create", "{item}"})
    String itemGet(Model model, @PathVariable Optional<Item> item) {
        if (!item.isPresent()) {
            model.addAttribute("item", new Item());
        } else {
            model.addAttribute("item", item.get());
        }
        return "items/item-form";
    }

    @PostMapping(path = {"create"})
    String itemPost(Item item) {
        itemRepository.save(item);
        return "redirect:";
    }

    @GetMapping("{item}/add-stock")
    String getAddStock(Model model, @PathVariable Item item) {
        return "items/add-stock";
    }


    @GetMapping("{item}/print-barcode")
    String printBarcode(Model model, @PathVariable Item item) {
        return "items/print-barcode";
    }


    @PostMapping("{item}/add-stock")
    String getAddStock(Model model, @PathVariable Item item, Integer amount) {
        if (amount != null) {
            if (item.stock != null)
                item.stock += amount;
            else item.stock = amount;
        }
        itemRepository.save(item);
        return "redirect:/item";
    }
}
