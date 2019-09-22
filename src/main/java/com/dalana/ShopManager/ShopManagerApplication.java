package com.dalana.ShopManager;

import com.dalana.ShopManager.Auth.User;
import com.dalana.ShopManager.Auth.UserRepository;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopManagerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShopManagerApplication.class, args);

    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
