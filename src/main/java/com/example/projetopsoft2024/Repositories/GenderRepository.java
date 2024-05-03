package com.example.projetopsoft2024.Repositories;

import com.example.projetopsoft2024.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender,Long> {

    @Query("SELECT f from Gender f where f.genderId = :genderId")
    List<Gender> findByGenderId(@Param("genderId") String genderId);
}