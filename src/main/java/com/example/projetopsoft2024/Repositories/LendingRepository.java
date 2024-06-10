package com.example.projetopsoft2024.Repositories;


import com.example.projetopsoft2024.models.Entitys.Lending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LendingRepository extends JpaRepository<Lending,Long> {

    @Query("SELECT l FROM Lending l JOIN l.books b WHERE b.isbn = :bookId")
    List<Lending> findLendingByBookId(Long bookId);

    @Query("SELECT l FROM Lending l JOIN l.user lu WHERE lu.readernumber = :userId")
    List<Lending> findLendingByUserId(Long userId);

    @Query("SELECT g.description, COUNT(l) * 1.0 " +
            "FROM Lending l " +
            "JOIN l.books b " +
            "JOIN b.gender g " + // Corrigi "gender" para "genre"
            "WHERE FUNCTION('MONTH', l.startDate) = :month " +
            "AND FUNCTION('YEAR', l.startDate) = :year " +
            "GROUP BY g.genderId")
    List<Object[]> countLendingsPerGenre( int month,int year);

    @Query("SELECT COUNT(l) FROM Lending l WHERE l.user.readernumber = :userId AND l.startDate >= :oneYearAgo")//
    Long countLendingsByUserIdFromLastYear(@Param("userId") Long userId, @Param("oneYearAgo") LocalDate oneYearAgo);//
    @Query("SELECT g.description, l.user, COUNT(l) FROM Lending l JOIN l.books b JOIN b.gender g WHERE l.startDate >= :startDate AND l.startDate <= :endDate GROUP BY g.description, l.user")//
    List<Object[]> countLendingsPerGenreAndUser(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);//

    @Query("SELECT l FROM Lending l WHERE l.startDate >= :startDate")
    List<Lending> findAllLendingsSince(java.time.LocalDate startDate);//

}





