package com.example.projetopsoft2024.usermanagement.model;
//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;


//@Schema(description = "User")
@Entity
@Getter
@Setter
public class User {

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

    @Version
    private long version;

    public User() {
        setName(name);
    }
    public User(final String name, final String email, final Date dateofbirth, final Long phonenumber, final String GDPRconsent) {
        setName(name);
        setEmail(email);
        setDateofbirth(dateofbirth);
        setPhonenumber(phonenumber);
        setGDPRconsent(GDPRconsent);


    }
}


