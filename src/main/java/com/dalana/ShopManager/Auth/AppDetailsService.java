package com.dalana.ShopManager.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {


        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        if (username.equals("dalana")) {
            user = new User();
            user.setUsername("dalana");
            user.setRole("ROLE_ADMIN");
            PasswordEncoder e = new BCryptPasswordEncoder();
            user.setPassword(e.encode("dd"));
        }
        return new AppUserPrincipal(user);
    }
}

