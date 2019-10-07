package com.dalana.ShopManager.POS;

import com.dalana.ShopManager.Auth.UserRepository;
import com.dalana.ShopManager.Inventory.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("bill")
@Controller
public class BillController {

    private final
    BillRepo billRepo;
    private final
    UserRepository userRepository;

    public BillController(BillRepo billRepo, UserRepository userRepository) {
        this.billRepo = billRepo;
        this.userRepository = userRepository;

    }

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
    @GetMapping("{bill}/print")
    private String PrintBill(Model model, @PathVariable Bill bill) {
        model.addAttribute("bill", bill);
        return "pos/print";
    }

    @GetMapping(value = "{bill}", params = {"itemCode"})
    private String addItem(@PathVariable Bill bill, @RequestParam("itemCode") Item itemCode, RedirectAttributes redir) {
        if (itemCode != null) {
            if (itemCode.getStock() < (bill.getQuantity(itemCode) + 1))
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
