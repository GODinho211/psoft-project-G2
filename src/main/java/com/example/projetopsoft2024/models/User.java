package com.example.projetopsoft2024.models;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Schema(description = "User")
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

    @Column(name = "name", nullable = false, unique = false, updatable = false)
    private String name;

    @Email(message = "O email deve estar em um formato válido")
    @Column(name = "email", nullable = false, unique = true)
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


