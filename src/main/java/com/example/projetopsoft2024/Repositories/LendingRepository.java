package com.example.projetopsoft2024.Repositories;


import com.example.projetopsoft2024.models.Lending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendingRepository extends JpaRepository<Lending,Long> {

    @Query("SELECT l FROM Lending l JOIN l.books b WHERE b.isbn = :bookId")
    List<Lending> findLendingByBookId(Long bookId);

    @Query("SELECT l FROM Lending l JOIN l.user lu WHERE lu.userId = :userId")
    List<Lending> findLendingByUserId(Long userId);

    @Query("SELECT l FROM Lending l WHERE l.user.userId = :userId AND l.fine = true")
    List<Lending> findLendingByUserIdAndFineIsTrue(Long userId);

    @Query("SELECT l FROM Lending l JOIN l.books b WHERE b.isbn = :bookId AND l.returnDate IS NULL")
    List<Lending> findLendingByBookIdAndReturnDateIsNull(Long bookId);
}
