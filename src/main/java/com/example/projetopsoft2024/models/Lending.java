package com.example.projetopsoft2024.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "lending")
public class Lending {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long lendingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinTable(
            name = "lending_book",
            joinColumns = @JoinColumn(name = "lending_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "return_date", nullable = true)
    private Date returnDate;

    public Lending(User user, List<Book> books, Date startDate) {
        this.user = user;
        this.books = books;
        this.startDate = startDate;
    }

}