package com.example.projetopsoft2024.Repositories;

import com.example.projetopsoft2024.models.Author;
import com.example.projetopsoft2024.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    @Query("SELECT b FROM Book b WHERE b.author.name = :name")
    List<Book> findBooksByAuthor(@Param("name")String name);
    @Query("SELECT a FROM Author a WHERE a.idAuthor = :idAuthor")
    Optional<Author> findById(Long idAuthor);

    @Query("SELECT a FROM Author a WHERE a.name LIKE CONCAT(:name, '%')")
    List<Author> findByName(@Param("name") String name);


    @Query("SELECT a, COUNT(l) AS lendCount FROM Lending l JOIN l.books b JOIN b.author a GROUP BY a.id ORDER BY lendCount DESC")
    List<Object[]> findTopAuthors();
}
