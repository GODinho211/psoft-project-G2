package com.example.projetopsoft2024.Bootstrap;

import com.example.projetopsoft2024.Repositories.AuthorRepository;
import com.example.projetopsoft2024.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthorDataLoader implements CommandLineRunner {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorDataLoader(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialize authors
        List<Author> authors = Arrays.asList(
                new Author("Author 1", "Bio 1"),
                new Author("Author 2", "Bio 2"),
                new Author("Author 3", "Bio 3")
                // Add more authors as needed
        );

        // Save the authors to the database
        authorRepository.saveAll(authors);
    }
}
