package com.example.projetopsoft2024.Repositories;


import com.example.projetopsoft2024.models.Lending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LendingRepository extends JpaRepository<Lending,Long> {
}
