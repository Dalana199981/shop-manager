package com.dalana.ShopManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("pos")
@Controller
public class POSController {
    @Autowired
    BillRepo billRepo;
    @GetMapping
    String getHomePage( Model model,Pageable pageable) {
        model.addAttribute("bills",billRepo.findTop(pageable));
        return "pos/home";
    }

}
