package com.dalana.ShopManager;

import com.dalana.ShopManager.Inventory.ItemRepository;
import com.dalana.ShopManager.POS.BillRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Controller
public class MainController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    BillRepo billRepo;

    @GetMapping
    String Index(Model model) {
        model.addAttribute("itemCount", itemRepository.count());
        model.addAttribute("revenue", billRepo.lastDayRevenue());
        return "index";
    }
}
