package com.example.projetopsoft2024.models;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book")
public class Book  {

    @Id
    @Column(name = "book_id", nullable = false, unique = true, updatable = false)
    private long isbn;

    @Column(name = "title", nullable = false, unique = false, updatable = true)
    private String title;

    @Column(name = "description", nullable = true, unique = false, updatable = true)
    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "book_gender",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "gender_id")
    )
    private List<Gender> gender; // cada livro tem uma lista de generos a que pertence

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Author author;

    public Book(String title, String description, List<Gender> gender, Author authorOfBook) {
        this.title = title;
        this.description = description;
        this.gender = gender;
        this.author = authorOfBook;
    }
}
