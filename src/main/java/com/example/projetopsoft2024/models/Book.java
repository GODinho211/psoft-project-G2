package com.example.projetopsoft2024.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    public Book( long isbn,String title, String description ) {
        this.isbn= isbn;
        this.title = title;
        this.description = description;
    }

}
