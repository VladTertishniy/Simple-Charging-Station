package com.extrawest.simplechargingstationtertyshniy.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private long phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

    protected User(String email, String password, long phoneNumber, String name, Role role) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.role = role;
    }
}
