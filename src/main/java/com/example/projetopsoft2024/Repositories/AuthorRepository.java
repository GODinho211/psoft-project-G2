package com.example.projetopsoft2024.Repositories;

import com.example.projetopsoft2024.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT a FROM Author a WHERE a.name = :name")
    List<Author> findByName(String name);
    @Query("SELECT a FROM Author a WHERE a.idAuthor = :idAuthor")
    Optional<Author> findById(Long idAuthor);
}
