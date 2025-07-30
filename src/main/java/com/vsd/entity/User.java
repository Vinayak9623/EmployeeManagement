package com.vsd.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users_details")
public class User {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;

}
