package com.example.projetopsoft2024.models;
//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;


//@Schema(description = "User")
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="userprofile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "name", nullable = false, unique = true, updatable = true)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "dateofbirth")
    private Date dateofbirth;

    @Column(name = "phonenumber", nullable = false)
    private Long phonenumber;

    @Column(name = "readernumber", nullable = true)
    private Long readernumber;


    @Column(name = "gdprconsent", nullable = true)
    private String gdprconsent;


    public User(final String name, final String email, final Date dateofbirth, final Long phonenumber, final Long readernumber, final String gdprconsent) {
        setName(name);
        setEmail(email);
        setDateofbirth(dateofbirth);
        setPhonenumber(phonenumber);
        setReadernumber(readernumber);
        setGdprconsent(gdprconsent);


    }
}


