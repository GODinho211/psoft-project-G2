package com.example.projetopsoft2024.Repositories;


import com.example.projetopsoft2024.models.DTO.LendingsPerMonthDTO;
import com.example.projetopsoft2024.models.Entitys.Lending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LendingRepository extends JpaRepository<Lending,Long> {

    @Query("SELECT l FROM Lending l JOIN l.books b WHERE b.isbn = :bookId")
    List<Lending> findLendingByBookId(Long bookId);

    @Query("SELECT l FROM Lending l JOIN l.user lu WHERE lu.readernumber = :userId")
    List<Lending> findLendingByUserId(Long userId);

    @Query("SELECT g.description, COUNT(l) FROM Lending l JOIN l.books b JOIN b.gender g GROUP BY g.description")
    List<Object[]> countLendingsPerGenre();

    @Query("SELECT COUNT(l) FROM Lending l WHERE l.user.readernumber = :userId AND l.startDate >= :oneYearAgo")//
    Long countLendingsByUserIdFromLastYear(@Param("userId") Long userId, @Param("oneYearAgo") LocalDate oneYearAgo);//

}





