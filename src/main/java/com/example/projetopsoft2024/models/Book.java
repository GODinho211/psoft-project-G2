package com.example.projetopsoft2024.models;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="book")
public class Book {

    @Id
    @Column(name = "book_id", nullable = false, unique = true, updatable = false)
    private long isbn;

    @Column(name = "title", nullable = false, unique = false, updatable = true)
    private String title;

    @Column(name = "book_description", nullable = true, unique = false, updatable = true)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id")
    )
    private List<Gender> gender= new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id")
    )
    private List<Gender> gender= new ArrayList<>();


    //NAO TENHO A CERTEZA SE É ASSIM
    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "gender_id")
    //private Gender gender;



    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "autor_id")
    //private Author author;

 //   public Book(String title, String description, List<Gender> gender, Author authorOfBook) {
 //       this.title = title;
 //       this.description = description;
 //       this.gender = gender;
 //       this.author = authorOfBook;
 //   }

    public Book(long isbn, String title, String description) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
    }

}
