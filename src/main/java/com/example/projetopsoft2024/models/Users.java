package com.example.projetopsoft2024.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "email", nullable = false)
    @NotNull
    private String email;

    @Column(name = "dateofbirth")
    private Date dateofbirth;

    @Column(name = "phonenumber", nullable = false)
    private Long phonenumber;

    @Column(name = "GDPRconsent", nullable = false)
    @NotNull
    private String GDPRconsent;


    public Users() {
        setName(name);
    }
    public Users(final String name, final String email, final Date dateofbirth, final Long phonenumber, final String GDPRconsent) {
        setName(name);
        setEmail(email);
        setDateofbirth(dateofbirth);
        setPhonenumber(phonenumber);
        setGDPRconsent(GDPRconsent);


    }
}