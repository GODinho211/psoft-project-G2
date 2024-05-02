package com.example.projetopsoft2024.Repositories;

import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT f from Book f where f.title = :title")
        List<Book> findByTitle(@Param("title") String title);

        List<Book> findByGender(Gender gender);

    @Query("SELECT b FROM Book b JOIN FETCH b.gender")
    List<Book> findAllWithGender();

}
