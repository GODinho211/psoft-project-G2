package com.example.projetopsoft2024.models.Entitys;

import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.DTO.LendingDTO;
import com.example.projetopsoft2024.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    @Column(name = "start_date", nullable = true) //if null, should get the current date
    private LocalDate startDate;

    @Column(name = "return_date", nullable = true)
    private LocalDate returnDate; //this is null till the return happen or received by client

    private float fine;

    public static final long MAX_LOAN_PERIOD = 15; // Max loan period in days
    private static final float FINE_PER_DAY = 1.0f;

    public Lending (User user, List<Book> books, LocalDate startDate, LocalDate returnDate){
        this.user = user;
        this.books = books;
        this.startDate = startDate != null ? startDate : LocalDate.now();
        this.returnDate = returnDate;
    }

    public LendingDTO toLendingDTO() {
        return new LendingDTO(this.lendingId, this.user, this.books, this.startDate);
    }

    //to calc the fine value, this should be call on return
    public void fineCalc(LocalDate startDate, LocalDate returnDate){
        long daysBetween = ChronoUnit.DAYS.between(startDate, returnDate);
        if (daysBetween > MAX_LOAN_PERIOD) {
            this.fine = (daysBetween - MAX_LOAN_PERIOD) * FINE_PER_DAY;
        } else {
            this.fine = 0; // Sem multa se o retorno estiver dentro do prazo
        }

   }

    private boolean countLending;//

    public boolean getCountLending() {
        return this.countLending;
    }//

    public long getDaysOverdue() {
            return ChronoUnit.DAYS.between(this.startDate, returnDate);
    }

    public LocalDate getLendDate() {
        return this.startDate;
    }//

    public Book getBook() {
        return this.books.get(0);
    }//


}