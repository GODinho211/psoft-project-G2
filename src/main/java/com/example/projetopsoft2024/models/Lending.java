package com.example.projetopsoft2024.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private LocalDate startDate;

    @Column(name = "return_date", nullable = true)
    private LocalDate returnDate = null;

    private boolean fine = false;

    public Lending(User user, List<Book> books, LocalDate startDate) {
        this.user = user;
        this.books = books;
        this.startDate = startDate;
        this.returnDate = startDate.plusDays(15); // Define a data de retorno como 15 dias após a data de início
    }
    @PrePersist
    public void setDates() {
        this.startDate = LocalDate.now();
    }

}