package com.dalana.ShopManager.Auth;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String role;

    @Column
    private String password;

    // standard getters and setters
}