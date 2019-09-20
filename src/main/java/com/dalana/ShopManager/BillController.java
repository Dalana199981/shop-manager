package com.dalana.ShopManager;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;

@RequestMapping("bill")
@Controller
public class BillController {

    @Autowired
    BillRepo billRepo;

    @GetMapping("")
    String createBill(Model model, String customer) {
        Bill bill = new Bill();
        if (customer == null || customer.isEmpty()) customer = "Walk-In-Customer";
        bill.setCustomer(customer);
        this.billRepo.save(bill);
        return "redirect:" + bill.id;
    }

    @GetMapping("{bill}")
    private String getBill(Model model, @PathVariable Bill bill) {
        model.addAttribute("bill", bill);
        return "pos/bill";
    }

    @GetMapping(value = "{bill}", params = {"itemCode"})
    private String addItem(@PathVariable Bill bill, @RequestParam("itemCode") Item itemCode, RedirectAttributes redir) {
        if (itemCode != null) {
            if (itemCode.stock < (bill.getQuantity(itemCode) + 1))
                redir.addFlashAttribute("error", "NO ENOUGH STOCK");
            else {
                bill.addItem(itemCode);
                billRepo.save(bill);
            }
        } else {
            redir.addFlashAttribute("error", "NOT FOUND");
        }
        return "redirect:" + bill.id;
    }

    @GetMapping(value = "{bill}", params = {"remove"})
    private String removeItem(@PathVariable Bill bill, @RequestParam("remove") Item itemCode) {
        if (itemCode != null) {
            bill.removeItem(itemCode);
            billRepo.save(bill);
        }
        return "redirect:" + bill.id;
    }

    @GetMapping(value = "{bill}", params = {"complete"})
    private String Complete(@PathVariable Bill bill, @RequestParam("complete") Boolean complete) {
        if (complete != null) {
            bill.complete();
            billRepo.save(bill);
        }
        return "redirect:" + bill.id;
    }

    @GetMapping(value = "{bill}", params = {"amount"})
    private String Pay(@PathVariable Bill bill, @RequestParam("amount") Float amount) {
        if (amount != null) {
            bill.pay(amount);
            billRepo.save(bill);
        }
        return "redirect:" + bill.id;
    }
}
