package com.example.projetopsoft2024.Repositories;

import com.example.projetopsoft2024.models.Book;
import com.example.projetopsoft2024.models.Gender;
import com.example.projetopsoft2024.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByIsbn(long isbn);

    List<Book> findByAuthor(Author author);

    Page<Book> findAll(Pageable pageable);


    @Query("SELECT f from Book f where lower(f.title) LIKE lower(concat('%', :title, '%'))")
    List<Book> findByTitle(@Param("title") String title);

        List<Book> findByGender(Gender gender);

    @Query("SELECT b FROM Book b JOIN FETCH b.gender")
    List<Book> findAllWithGender();

    @Query("SELECT b FROM Book b JOIN b.gender g WHERE lower(g.description) LIKE lower(concat('%', :description, '%'))")
    List<Book> findByGenderDescription(@Param("description") String description);

    @Query("SELECT b FROM Book b WHERE lower(b.author.name) LIKE lower(concat('%', :authorName, '%'))")
    List<Book> findByAuthorName(@Param("authorName") String authorName);

}
