package com.dalana.ShopManager.Auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UsersController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    String Index(Model model) {
        model.addAttribute("users", userRepository
                .findAll());
        return "users/home";
    }

    @GetMapping("create")
    String CreateUser(Model model) {
        model.addAttribute("user", new User());
        return "users/user";
    }

    @PostMapping("create")
    String CreateUser(Model model, User user, String newPassword) {
        BCryptPasswordEncoder e = new BCryptPasswordEncoder();
        if (!newPassword.equals("")) {
            System.out.println("newPassword");
            user.setPassword(e.encode(newPassword));
        } else {
            User oldUser = userRepository.findByUsername(user.getUsername());
            user.setPassword(oldUser.getPassword());
        }
        userRepository.save(user);
        return "redirect:";
    }

    @GetMapping("{user}")
    String EditUser(Model model, @PathVariable User user) {
        user.setPassword(null);
        model.addAttribute("user", user);
        return "users/user";
    }
}
